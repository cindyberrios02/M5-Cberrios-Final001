const validateLoginForm = (form) => {
    const username = form.username.value;
    const password = form.password.value;
    const errors = [];

    if (username.trim().length < 3) {
        errors.push('El nombre de usuario debe tener al menos 3 caracteres');
    }

    if (password.trim().length < 6) {
        errors.push('La contraseña debe tener al menos 6 caracteres');
    }

    if (errors.length > 0) {
        showErrors(errors);
        return false;
    }
    return true;
};

const validateRegistrationForm = (form) => {
    const nombre = form.nombre.value;
    const email = form.email.value;
    const username = form.username.value;
    const password = form.password.value;
    const confirmPassword = form.confirmPassword.value;
    const fechaNacimiento = form.fechaNacimiento.value;
    const errors = [];

    if (nombre.trim().length < 3) {
        errors.push('El nombre debe tener al menos 3 caracteres');
    }

    if (!email.match(/^[^\s@]+@[^\s@]+\.[^\s@]+$/)) {
        errors.push('El email no es válido');
    }

    if (username.trim().length < 3) {
        errors.push('El nombre de usuario debe tener al menos 3 caracteres');
    }

    if (password.length < 6) {
        errors.push('La contraseña debe tener al menos 6 caracteres');
    }

    if (password !== confirmPassword) {
        errors.push('Las contraseñas no coinciden');
    }

    if (!fechaNacimiento) {
        errors.push('La fecha de nacimiento es obligatoria');
    }

    if (errors.length > 0) {
        showErrors(errors);
        return false;
    }
    return true;
};

const showErrors = (errors) => {
    const errorDiv = document.getElementById('errorMessages');
    errorDiv.innerHTML = '';
    errorDiv.className = 'alert alert-danger mt-3';

    const ul = document.createElement('ul');
    ul.className = 'mb-0';
    errors.forEach(error => {
        const li = document.createElement('li');
        li.textContent = error;
        ul.appendChild(li);
    });
    errorDiv.appendChild(ul);
};