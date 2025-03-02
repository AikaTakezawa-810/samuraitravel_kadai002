const stripe = Stripe('pk_test_51QlrMdB4PH2TrKrlGKLPOCNuyFtpnpk71gnHtqpMNTsLjJ4C6ZFf8ymYOBIKeKBrv3EarnhfzJYGKJbY697KtuU100Pn3a51iz');
const paymentButton = document.querySelector('#paymentButton');

paymentButton.addEventListener('click', () => {
	stripe.redirectToCheckout({
	sessionId: sessionId
})
});