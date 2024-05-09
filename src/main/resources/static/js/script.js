function toggleForm() {
    let form_container = document.querySelector(".form-container");
    let show_btn = document.querySelector(".show-btn");
    if (form_container.style.display === "") {
        form_container.style.display = "flex";
        show_btn.style.display = "none";
    } else {
        form_container.style.display = "";
        show_btn.style.display = "inline-block";
    }
}

window.onload = function() {
    // Получаем все параметры из URL
    let urlParams = new URLSearchParams(window.location.search);

    // Создаем объект для хранения параметров и их значений
    let paramsObject = {};

    // Заполняем объект параметрами и их значениями
    for (let [key, value] of urlParams.entries()) {
        paramsObject[key] = value;
    }

    // Проходим по всем параметрам и устанавливаем их значения в соответствующие элементы input
    for (let key in paramsObject) {
        let inputElement = document.getElementById(key); // Предполагается, что у вас есть элемент input с id, совпадающим с ключом параметра
        if (inputElement && paramsObject[key]) {
            inputElement.value = paramsObject[key];
        }
    }
};