package rio.antelodel.david.ejercicios_programacion.auxiliar.latex_document;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import rio.antelodel.david.ejercicios_programacion.auxiliar.latex_document.encoder.ILatexEncoder;
import rio.antelodel.david.ejercicios_programacion.auxiliar.latex_document.encoder.LaTexEncoder;
import rio.antelodel.david.ejercicios_programacion.auxiliar.logger.CustomLogger;
import rio.antelodel.david.ejercicios_programacion.auxiliar.pair.Pair;
import rio.antelodel.david.ejercicios_programacion.controller.utility.archivo_handler.IArchivoHandlerConfig;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicio;

public class LaTexDocument implements ILaTexDocument {
	
	// Tags
	
	private static final String DOCUMENT_CLASS = "\\documentclass{article}";
	private static final String DOCUMENT_BEGIN = "\\begin{document}";
	private static final String DOCUMENT_TITLE_BEGIN = "\\title{";
	private static final String MAKE_TITLE = "\\maketitle";
	private static final String DATE_BEGIN = "\\date{";
	private static final String DATE_SEPARATOR = "\\\\";
	private static final String DOCUMENT_END = "\\end{document}";
	private static final String END = "}";
	
	private static final String LISTING_BEGIN = "\\begin{lstlisting}";
	private static final String LISTING_END = "\\end{lstlisting}";
	
	private static final String BIG_SKIP = "\\bigskip";
	
	private static final String NEW_LINE = "\n";
	
	private static final String CODIFICATION_1 = "\\usepackage[T1]{fontenc}";
	private static final String CODIFICATION_2 = "\\usepackage{listings}";
	
	private static final String LISTING_CONFIG = 	"\\lstset{\r\n" + 
			"	basicstyle=\\normalsize\\ttfamily,\r\n" + 
			"	breaklines=true,\r\n" + 
			"	breakindent=0pt,\r\n" + 
			"	framextopmargin=10pt,\r\n" + 
			"	framexbottommargin=10pt,\r\n" + 
			"	frame=single,\r\n" + 
			"   mathescape\r\n" +
			"}";
	
	// Properties
	
	private String header;
	private Calendar date;
	private List<Pair<IREjercicio, Integer>> iREjerciciosPosition = new ArrayList<>();
	
	// Encoder
	
	private ILatexEncoder iLatexEncoder = new LaTexEncoder();
	
	// Getters & Setters
	
	public String getHeader () { return header; }
	public void setHeader (String title) { this.header = title; }
	
	public Calendar getDate () { return date; }
	public void setDate (Calendar date) { this.date = date; }
	
	public List<Pair<IREjercicio, Integer>> getiREjerciciosPosition () { return iREjerciciosPosition; }
	public void setIREjerciciosPosition (List<Pair<IREjercicio, Integer>> iREjerciciosPosition) { this.iREjerciciosPosition = iREjerciciosPosition; }
	
	// Other
	
	private String getCode () {
		
		StringBuilder code = 	new StringBuilder(	DOCUMENT_CLASS + NEW_LINE
													+ CODIFICATION_1 + NEW_LINE
													+ CODIFICATION_2 + NEW_LINE
													+ LISTING_CONFIG + NEW_LINE
													+ DOCUMENT_TITLE_BEGIN + getHeader() + END + NEW_LINE
													+ DATE_BEGIN + date.get(Calendar.YEAR)
													+ DATE_SEPARATOR + ILaTexDocumentHelper.getMonthName(date.get(Calendar.MONTH)) + END + NEW_LINE
													+ DOCUMENT_BEGIN + NEW_LINE
													+ MAKE_TITLE + NEW_LINE);
		
		int i = 1;
		
		for (Pair<IREjercicio, Integer> p : iREjerciciosPosition) {
			
			code.append("\\noindent \\textbf{Ejercicio " + i + "}" + NEW_LINE + BIG_SKIP + NEW_LINE + LISTING_BEGIN + NEW_LINE + parseText(p.getFirst().getEnunciado()) + NEW_LINE + LISTING_END + NEW_LINE + BIG_SKIP + NEW_LINE);
			i++;
			
		}
		
		code.append(DOCUMENT_END);
		
		return iLatexEncoder.encode(code.toString());
		
	}
	
	public byte [] getLaTex () {
		
		return getCode().getBytes();
		
	}
	
	@Override
	public byte [] getPDF () {
		
		String code = getCode();
		
		String pathDir = IArchivoHandlerConfig.PATH_MAIN + File.separator + code.hashCode();
		String pathTex = pathDir + File.separator + "document.tex";
		String pathPDF = pathDir + File.separator + "document.pdf";
		
		try {
		
			// Create dirs
			
			Files.createDirectories(Paths.get(pathDir));
			
			// Create Tex file
			
			Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(pathTex), StandardCharsets.UTF_8.toString()));
			
			try { out.write(getCode()); }
			finally { out.close(); }
			
			File fileTex = new File(pathTex);
			
			// Make PDF file
			
			Process process = Runtime.getRuntime().exec("xelatex -output-directory " + FilenameUtils.getFullPath(fileTex.getAbsolutePath()) + " " + fileTex.getAbsolutePath());
			process.waitFor();
			
			// Get PDF
			
			byte [] fileResult = Files.readAllBytes(Paths.get(pathPDF));
			
			// Delete dirs
			
			FileUtils.deleteDirectory(new File(FilenameUtils.getFullPath(fileTex.getAbsolutePath())));
			
			return fileResult;
			
		}
		
		catch (Exception e) {
			
			CustomLogger.LOGGER.severe("Failed retrieving PDF, triggered exception: " + e.toString());
			return new byte[0];
			
		}
		
	}
	
	private String parseText (String text) {
		
		return text.replace("\\", "\\textbackslash");
		
	}
	
}
