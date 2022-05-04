function getOTP() {
    let email = $('#cusEmail').val()
    if(email==null|| email.trim().length==0)
    {
        alert("Vui lòng nhập email")
        return
    }
    fetch('/TourismManagement/dang-ki-tai-khoan/otp', {
        method: 'post',
        body: JSON.stringify({
            "email": email
        }),
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(res => res.json()).then(data => {
        console.info(data)
        $('#accUsername').attr("disabled", true);
        $('#accPassword').attr("disabled", true)
        $('#accFirstName').attr("disabled", true)
        $('#accLastName').attr("disabled", true)
        $('#accSex').attr("disabled", true)
        $('#accPhoneNumber').attr("disabled", true)
        $('#accIdCard').attr("disabled", true)
        $('#accDateOfBirth').attr("disabled", true)
        $('#accAvatar').attr("disabled", true)
        $('#cusEmail').attr("disabled", true)
        $('#receiveOTPArea').show()
        confirmOTP(data['otp'])
    })
}
function confirmOTP(otp){
    $('#otpInp').keyup(function (){
        if($(this).val()===otp){
            $(this).attr('disabled', true)
            $('#signUpConfirmArea').show()
        }
    })
}
$(document).ready(function (){
    $('#loading').hide()
    $('#receiveOTPArea').hide()
    $('#signUpConfirmArea').hide()
    $('#otpSendBtn').click(function(){
        getOTP()
    })
    $('#signUpConfirmBtn').click(function(){
        alert("Xác nhận tạo tài khoản?")
        $(this).hide()
        $('#loading').show()
    })
})