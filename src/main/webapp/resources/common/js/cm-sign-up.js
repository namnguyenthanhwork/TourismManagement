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
        $('#accUsername').attr("readonly", true);
        $('#accPassword').attr("readonly", true)
        $('#accFirstName').attr("readonly", true)
        $('#accLastName').attr("readonly", true)
        $('#accSex').attr("readonly", true)
        $('#accPhoneNumber').attr("readonly", true)
        $('#accIdCard').attr("readonly", true)
        $('#accDateOfBirth').attr("readonly", true)
        $('#accAvatar').attr("readonly", true)
        $('#cusEmail').attr("readonly", true)

        confirmOTP(data['otp'])
    })
}
function confirmOTP(otp){
    $('#otpInp').keyup(function (){
        if($(this).val()===otp){
           $('#otpModal').modal('hide')
            $('#otpConfirmArea').hide()
            $('#signUpConfirmArea').show()
        }
    })
}
$(document).ready(function (){
    $('#loading').hide()
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