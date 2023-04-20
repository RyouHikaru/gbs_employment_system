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

function cancelAction() {
    window.history.go(-1);
}