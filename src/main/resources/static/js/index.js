function clearSearch() {
    document.getElementById("searchFirstName").value = "";
    document.getElementById("searchLastName").value = "";
    document.getElementById("searchPosition").value = "";
    const search = {
        firstName: "",
        lastName: "",
        position: ""
    };
    document.querySelector("form").setAttribute("modelAttribute", "search");
    document.querySelector("form").setAttribute("modelAttribute", JSON.stringify(search));
}

function returnToList() {
    window.location = '/employees/list';
}

function returnToEmployeeDetails(employeeId) {
    window.location = `/employees/employee/${employeeId}`;
}

function returnToCompensationSearch(employeeId) {
    window.location = `/compensation/search/${employeeId}`;
}

function returnToIndex() {
    window.location = '/';
}

function returnToPreviousPage() {
    history.back();
}

function addEmployee() {
    window.location = '/employees/new';
}