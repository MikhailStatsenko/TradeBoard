function showPhoneNumber(phoneNumber, id) {
    const btnElement = document.getElementById(id)
    btnElement.innerText = "Скопировано! " + phoneNumber;
    navigator.clipboard.writeText(phoneNumber)
        .then(() => {
            console.log('Phone number copied to clipboard');
        })
        .catch((error) => {
            console.error('Failed to copy phone number to clipboard:', error);
        });

    setTimeout(() => {
        btnElement.innerText = phoneNumber;
    }, 2000);
}