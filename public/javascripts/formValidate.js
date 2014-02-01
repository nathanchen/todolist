var isSubmit_Flag_confirmPwd = 0;
var isSubmit_Flag_username = 0;
var isSubmit_Flag_pwd = 0;
var isSubmit_Flag_regEmail = 0;
var isSubmit_Flag_fullname = 0;



$(function(){
    $("#fullname").click(function(){
        $("#fullname").removeClass("errorinput");
        $("#checkfullname").html("Your fullname");
    })
    $("#username").click(function(){
        $("#username").removeClass("errorinput");
        $("#usernamelabel").show();
        $("#checkusername").html("3-50 English letters");
    })
    $("#password").click(function(){
        $("#password").removeClass("errorinput");
        $("#checkpassword").html("Minimum 6 characters");
    })
    $("#repeatPassword").click(function(){
        $("#repeatPassword").removeClass("errorinput");
        $("#checkrepeatPassword").html("Please enter your password again");
    })
    $("#email").click(function(){
        $("#email").removeClass("errorinput");
        $("#emaillabel").show();
        $("#checkemail").html("Valid email address");
    })
    $("#inviter").click(function(){
        if($("#inviter").val() == "nobody"){
            $("#inviter").val("");
        }
        $("#checkinviter").html("People who invites you to us will have the chance to win bonus.");
    })

    $("#signup").click(function(){
        if(isSubmit_Flag_fullname * isSubmit_Flag_confirmPwd
                * isSubmit_Flag_username * isSubmit_Flag_pwd
                *  isSubmit_Flag_regEmail > 0){
            $('signupForm').submit();
        }
    })

    $("#fullname").blur(function(){
        if($(this).val().trim() == ""){
            $("#fullnamelabel").show();
            $("#checkfullname").show();
            $("#fullname").removeClass("successinput");
            $("#fullname").addClass("errorinput");
            isSubmit_Flag_fullname = 0;
            $("#checkfullname").html("");
        }else{
            $("#checkfullname").html("&nbsp;");
            $("#fullnamelabel").hide();
            $("#fullname").addClass("successinput");
            isSubmit_Flag_fullname = 1;
        }
    })

    $("#username").blur(function(){
        var reg_internetId= /\b(?![0-9_]+$)[a-zA-Z0-9_]{6,15}\b/;
        if($(this).val() == ""){
            $("#usernamelabel").show();
            $("#checkusername").show();
            $("#username").removeClass("successinput");
            $("#username").addClass("errorinput");
            isSubmit_Flag_username = 0;
            $("#checkusername").html("");
        }else if($(this).val().length > 15 || $(this).val().length < 6){
            $("#usernamelabel").show();
            $("#checkusername").show();
            $("#username").removeClass("successinput");
            $("#username").addClass("errorinput");
            isSubmit_Flag_username = 0;
            $("#checkusername").html("<font color='#F15A21'>User name can only have 6 to 15 letters</font>");
        }else if(!reg_internetId.test($(this).val())){
            $("#usernamelabel").show();
            $("#checkusername").show();
            $("#username").removeClass("successinput");
            $("#username").addClass("errorinput");
            isSubmit_Flag_username = 0;
            $("#checkusername").html("<font color='#F15A21'>English letters</font>");
        }else{
            $("#checkusername").html("&nbsp;");
            $("#checkusername").addClass("loading");
            $.ajax({
                type:"POST",
                async:true,
                url:"/validUsername/"+$(this).val(),
                success:function(msg_internetId){
                    if(msg_internetId == "1"){
                        $("#usernamelabel").hide();
                        $("#username").addClass("successinput");
                        $("#username").removeClass("errorinput");
                        $("#checkusername").removeClass("loading");
                        isSubmit_Flag_username = 1;
                    }else{
                        $("#usernamelabel").show();
                        $("#username").removeClass("successinput");
                        $("#username").addClass("errorinput");
                        $("#checkusername").html("<font color='#F15A21'>This username has been taken");
                        $("#checkusername").removeClass("loading");
                        isSubmit_Flag_username = 0;
                    }
                }
            });
        }
    })

    $("#password").blur(function(){
        var reg_pwd= /^.*[A-Za-z0-9\\w_-]+.*$/;
        if($(this).val() == ""){
            $("#passwordlabel").show();
            $("#checkpassword").show();
            $("#password").removeClass("successinput");
            $("#password").addClass("errorinput");
            isSubmit_Flag_pwd = 0;
            $("#checkpassword").html("");
        }else if($(this).val().length > 16 || $(this).val().length < 6){
            $("#passwordlabel").show();
            $("#checkpassword").show();
            $("#password").removeClass("successinput");
            $("#password").addClass("errorinput");
            isSubmit_Flag_pwd = 0;
            $("#checkpwd").html("<font color='#F15A21'>6 to 15 characters</font>");
        }else if(!reg_pwd.test($(this).val())){
            $("#passwordlabel").show();
            $("#checkpassword").show();
            $("#password").removeClass("successinput");
            $("#password").addClass("errorinput");
            isSubmit_Flag_pwd = 0;
            $("#checkpwd").html("<font color='#F15A21'>Letters, numbers and punctuation</font>");
        }else{
            $("#password").removeClass("errorinput");
            $("#password").addClass("successinput");
            $("#passwordlabel").hide();
            isSubmit_Flag_pwd = 1;
        }
    })

    $("#repeatPassword").blur(function(){
        var reg_confirmPwd= /^.*[A-Za-z0-9\\w_-]+.*$/;
        if($(this).val() == ""){
            $("#repeatPasswordlabel").show();
            $("#checkrepeatPassword").show();
            $("#repeatPassword").removeClass("successinput");
            $("#repeatPassword").addClass("errorinput");
            isSubmit_Flag_confirmPwd = 0;
            $("#checkpassword").html("");
        }else if($(this).val().length > 16 || $(this).val().length < 6){
            $("#repeatPasswordlabel").show();
            $("#checkrepeatPassword").show();
            $("#repeatPassword").removeClass("successinput");
            $("#repeatPassword").addClass("errorinput");
            isSubmit_Flag_confirmPwd = 0;
            $("#checkconfirmPwd").html("<font color='#F15A21'>6 to 15 characters</font>");
        }else if(!reg_confirmPwd.test($(this).val())){
            $("#repeatPasswordlabel").show();
            $("#checkrepeatPassword").show();
            $("#repeatPassword").removeClass("successinput");
            $("#repeatPassword").addClass("errorinput");
            isSubmit_Flag_confirmPwd = 0;
            $("#checkconfirmPwd").html("<font color='#F15A21'>Letters, numbers and punctuation</font>");
        }else if($(this).val() != $("#password").val()){
            $("#repeatPasswordlabel").show();
            $("#checkrepeatPassword").show();
            $("#repeatPassword").removeClass("successinput");
            $("#repeatPassword").addClass("errorinput");
            isSubmit_Flag_confirmPwd = 0;
            $("#checkconfirmPwd").html("<font color='#F15A21'>Password don't match</font>");
        }else{
            $("#repeatPassword").removeClass("errorinput");
            $("#repeatPassword").addClass("successinput");
            $("#repeatPasswordlabel").hide();
            isSubmit_Flag_confirmPwd = 1;
        }
    })

    $("#email").blur(function(){
        var reg_regEmail = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
        if($(this).val() == ""){
            $("#emaillabel").show();
            $("#checkemail").show();
            $("#email").removeClass("successinput");
            $("#email").addClass("errorinput");
            isSubmit_Flag_regEmail = 0;
            $("#checkemail").html("");
        }else if($(this).val().length > 50 ){
            $("#emaillabel").show();
            $("#checkemail").show();
            $("#email").removeClass("successinput");
            $("#email").addClass("errorinput");
            isSubmit_Flag_regEmail = 0;
            $("#checkemail").html("<font color='#F15A21'>Maximum 50 characters</font>");
        }else if(!reg_regEmail.test($(this).val())){
            $("#emaillabel").show();
            $("#checkemail").show();
            $("#email").removeClass("successinput");
            $("#email").addClass("errorinput");
            isSubmit_Flag_regEmail = 0;
            $("#checkemail").html("<font color='#F15A21'>Please check that your e-mail addresses match and try again</font>");
        }else{
            $("#checkemail").html("&nbsp;");
            $("#checkemail").addClass("loading");
            $.ajax({
                type:"POST",
                async:true,
                url:"/validEmail/"+$(this).val(),
                success:function(msg_regEmail){
                    if(msg_regEmail == "1"){
                        $("#emaillabel").hide();
                        $("#email").addClass("successinput");
                        $("#email").removeClass("errorinput");
                        $("#checkemail").removeClass("loading");
                        isSubmit_Flag_regEmail = 1;
                    }else{
                        $("#emaillabel").show();
                        $("#email").removeClass("successinput");
                        $("#email").addClass("errorinput");
                        $("#checkregEmail").html("<font color='#F15A21'>Email has been used<a href='http://www.hao24.cn/member/retrievePwd.jsp'>找回密码</a></font>");
                        $("#email").removeClass("loading");
                        isSubmit_Flag_regEmail = 0;
                    }
                }
            });

        }
    })

    $("#inviter").blur(function(){
        if($("#inviter").val() == ""){
            $("#inviter").val("可不填");
        }
        if($(this).val() == ""){
            $("#inviter_succeed").removeClass("succeed");
            $(this).removeClass("text_error");
            $("#checkinviter").html("");
        }else if($("#inviter").val() != "可不填"){
            $("#inviter_succeed").addClass("loading");
            $.ajax({
                type:"POST",
                async:true,
                url:"checkElement.chk?action=checkRecom&internetId="+$(this).val().toLowerCase(),
                success:function(msg_inviter){
                    if(msg_inviter == "0"){
                        $("#inviter_succeed").removeClass("loading");
                        $("#inviter_succeed").removeClass("succeed");
                        $("#checkinviter").html("<font color='#F15A21'>推荐人ID不存在</font>");
                        $("#inviter").addClass("text_error");
                    }else{
                        $("#inviter_succeed").removeClass("loading");
                        $("#inviter_succeed").addClass("succeed");
                        $("#checkinviter").html("");
                        $("#inviter").removeClass("text_error");
                        $("#inviterNo").attr("value",msg_inviter);
                    }
                }
            });
        }
    })
});
