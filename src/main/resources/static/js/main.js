function showAlert() {
	alert("The button was clicked!");
}

function addParameterForm() {

}

function addRow() {
	let listName = 'movies'; //list name in Catalog.class
	let fieldsNames = ['id', 'title', 'info']; //field names from Movie.class
	let rowIndex = document.querySelectorAll('.item').length; //we can add mock class to each movie-row

	let row = document.createElement('div');
	row.classList.add('row', 'item');

	fieldsNames.forEach((fieldName) => {
		let col = document.createElement('div');
		col.classList.add('col', 'form-group');
		if (fieldName === 'id') {
			col.classList.add('d-none'); //field with id - hidden
		}

		let input = document.createElement('input');
		input.type = 'text';
		input.classList.add('form-control');
		input.id = listName + rowIndex + '.' + fieldName;
		input.setAttribute('name', listName + '[' + rowIndex + '].' + fieldName);

		col.appendChild(input);
		row.appendChild(col);
	});

	document.getElementById('movieList').appendChild(row);
};