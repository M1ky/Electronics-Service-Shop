function showAlert() {
	alert('test')
	document.getElementById("parametersList").insertRow(-1).innerHTML =
		'<td>' +
		'<label for="parameter">Parametr</label>' +
		'</td>' +
		'<option value="0">Wybierz parametr</option>' +
		'<td><input th:field="*{riskList[__${status.index}__].rodzajZagrozenia}"/></td>' +
		'<td><input th:field="*{riskList[__${status.index}__].zagrozonaOsoba}"/></td>' +
		'<td><input th:field="*{riskList[__${status.index}__].wstepneRyzyko}"/></td>' +
		'<td><input th:field="*{riskList[__${status.index}__].przeciwdzialanie}"/></td>' +
		'<td><input th:field="*{riskList[__${status.index}__].koncoweRyzyko}"/></td>'
}

function addParameterForm() {

}

function addRow()
{
	console.log(params);
}

window.onload = function() {
	console.log(params);
}

