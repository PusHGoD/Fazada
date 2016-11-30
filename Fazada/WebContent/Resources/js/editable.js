$(function() {
	$.fn.editable.defaults.mode = 'inline';
	$('#firstName').editable({
		type : 'text',
		ajaxOptions : {
			contentType : 'application/json',
			dataType : 'text'
		},
		params : function(params) {
			var json = convertXEditableToJSON(params);
			return JSON.stringify(json);
		},
		url : '/fazadaws/account/update'
	});
	$('#lastName').editable({
		type : 'text',
		ajaxOptions : {
			contentType : 'application/json',
			dataType : 'text'
		},
		params : function(params) {
			var json = convertXEditableToJSON(params);
			return JSON.stringify(json);
		},
		url : '/fazadaws/account/update'
	});
	$('#dateOfBirth').editable({
		type : 'combodate',
		combodate : {
			maxYear : new Date().getFullYear()
		},
		ajaxOptions : {
			contentType : 'application/json',
			dataType : 'text'
		},
		params : function(params) {
			var json = convertXEditableToJSON(params);
			return JSON.stringify(json);
		},
		url : '/fazadaws/account/update'
	});
	$('#email').editable({
		type : 'text',
		ajaxOptions : {
			contentType : 'application/json',
			dataType : 'text'
		},
		params : function(params) {
			var json = convertXEditableToJSON(params);
			return JSON.stringify(json);
		},
		url : '/fazadaws/account/update'
	});
});

function convertXEditableToJSON(params) {
	var json = {};
	json["userName"] = params.pk;
	if (params.value != null) {
		v = params.value;
	} else {
		v = '';
	}
	if (json[params.name] != null) {
		if (!json[params.name].push) {
			json[params.name] = [ json[params.name] ];
		}
		json[params.name].push(v);
	} else {
		json[params.name] = v;
	}
	return json;
}