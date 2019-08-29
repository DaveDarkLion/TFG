// Table

function tableDisplay (body, list) {
					
	for (var i = 0; i < list.length; i++) {
		
		var row = document.createElement("tr");
		
		for (var j = 0; j < list[0].length; j++) {
					
			var element = document.createElement("td");
			element.innerHTML = list[i][j];
			
			row.appendChild(element);
			
		}
		
		body.appendChild(row);
			
	}
					
}

// Select

function selectDisplay (select, current, list) {

	for (var i = 0; i < list.length; i++) {

		var option = document.createElement("option");
		
		option.value = list[i].id;
		option.innerHTML = list[i].name
		if (current != null && current == list[i].id) option.selected = "selected";
		
	    select.appendChild(option);
	    
	}
	
}

function selectMultiDisplay (select, current, list) {

	for (var i = 0; i < list.length; i++) {

		var option = document.createElement("option");
		
		option.value = list[i].id;
		option.innerHTML = list[i].name
		for (var j = 0; j < current.length; j++) if (current[j] == list[i].id) option.selected = "selected";
		
	    select.appendChild(option);
	    
	}
	
}

// Checkbox

function checkboxCollectionDisplay (div, name, list, submit) {
	
	for (var i = 0; i < list.length; i++) {
		
		var checkbox = document.createElement("input");
		checkbox.className = "filled-in checkbox-indigo";
		checkbox.type = "checkbox";
		checkbox.id = list[i].checkboxId;
		checkbox.name = name + '[]';
		checkbox.value = list[i].checkboxValue;
		
		if (list[i].checked) checkbox.checked = true;
		
		var span = document.createElement("span");
		span.className = "text-strong";
		span.innerHTML = list[i].labelValue;
		
		var label = document.createElement('label');
		label.appendChild(checkbox);
		label.appendChild(span);
		
		var p = document.createElement("p");
		p.appendChild(label);
		
		div.appendChild(label);
		
		if (submit) checkbox.setAttribute('onclick', "document.getElementById('" + checkbox.form.id + "').submit();");
		
	}
	
}

// List

function listDisplay (listContainer, list) {

	for (var i = 0; i < list.length; i++) {
		
		var li = document.createElement('li');
		li.innerHTML = list[i];
		li.className = "collection-item href-aligned no-margin";
		
		listContainer.appendChild(li);
		
	}
	
}

// Other

// Disable element if text is blank

function disableWhenBlank (textId, buttonId) {

	var text = document.getElementById(textId);
	var button = document.getElementById(buttonId);
	
	if (text.value == '') button.disabled = true;
	else button.disabled = false;
	
}

// Submit form

function submitForm (formId, method, action) {
	
	var form = document.getElementById(formId);
	form.method = method;
	form.action = action;
	form.submit();
	
}

// Go to the previous page

function back () {
	
	  window.history.back();
	  
}

// Check if Array includes Element

function includes (array, element) {
	
	for (var i = 0; i < array.length; i++) if (array[i].id == element.id) return true;
	return false;
	
}