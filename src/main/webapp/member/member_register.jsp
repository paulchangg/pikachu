<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>註冊 比卡丘</title>
    <link rel="stylesheet" type="text/css" href="css/common/memberlogin_normalize.css">
    <link rel="stylesheet" type="text/css" href="css/common/icon-font-3/style.css">
    <link rel="stylesheet" type="text/css" href="css/basic.css">
    <link rel="stylesheet" type="text/css" href="css/common/style.css">

    <script src="js/jquery.min.js"></script>
    <script defer="" src="js/modernizr.js"></script>
</head>
<body class="mmenu-show">
    <div class="wrapper">
        <header class="site-header">
            <div class="container">
                <div class="site-title"><a href="javascript:void(0)" class="logo ibtn">比卡丘</a></div>

                <div class="top-nav">
                    <ul class="func-menu list-inline">
                        <li class="bg"><a href="login.html"><i class="ic ic3-member"></i>會員登入</a></li>
                        <li class="bg"><a href="javascript:void(0)"><i class="ic ic3-cash"></i>信用卡比較</a>
                        <li><a href="javascript:void(0)"><i class="ic ic3-pen"></i>Q&A</a></li>
                        </li>
                        <li id="" class="gocart hover-on"><a href="#gocart" class="toggleBtn aaa"><i
                                    class="ic ic3-cart"></i>Shopping Cart <span>0</span></a>
                            <div class="cart-box">
                                <ul class="cart-list">
                                </ul>
                                <a href="javascript:void(0)" class="btn"><i class="ic ic3-cash"></i> 前往結帳</a>
                            </div>
                        </li>
                    </ul>
                </div>
                <nav class="site-nav">
                    <ul class="menu list-h">
                        <li class="has-child">
                            <a href="javascript:void(0)">關於我們</a>
                            <ul>
                                <li><a href="javascript:void(0)">關於我們</a></li>
                            </ul>
                            <span class="toggle-submenu"></span>
                        </li>
                        <li><a href="javascript:void(0)">聯絡我們</a></li>
                        <li class="has-child"><a href="javascript:void(0)">會員專區</a>
                            <ul>
                                <li><a href="login.html">會員登入</a></li>
                                <li><a href="member_register.html">會員註冊</a></li>
                                <li><a href="javascript:void(0)">忘記密碼</a></li>
                            </ul>
                            <span class="toggle-submenu"></span>
                        </li>
                        <li><a href="javascript:void(0)">論壇交友</a></li>
                        <li class="m-hide has-child"><a href="javascript:void(0)">商城</a>
                            <ul>
                                <li><a href="javascript:void(0)">線上購物</a></li>
                                <li><a href="javascript:void(0)">線上購物</a></li>
                                <li><a href="javascript:void(0)">線上購物</a></li>
                            </ul>
                            <span class="toggle-submenu"></span>
                        </li>
                        <li><a href="javascript:void(0)">資訊網</a></li>
                    </ul>
                </nav>
            </div>
        </header>

        <main class="site-main has-side">
            <div class="main-content">
                <div class="container">
                    <header class="content-top clearfix">
                        <div class="main-title">
                            <span>MEMBER SHOPPING ZONE</span>
                            <div class="second_txt">會員專區</div>
                        </div>
                    </header>
                    <aside class="side">
                        <ul class="side-nav list-v">
                            <li><a href="login.html">會員登入</a></li>
                            <li><a href="member_register.html">會員註冊</a></li>
                            <li><a href="javascript:void(0)">忘記密碼</a></li>
                        </ul>
                    </aside>
                    <section class="content">
                        <div class="content-title"><span>會員註冊</span></div>
                        <div class="form-wrap type3 line">
                            <div class="form-box">
                                <form id="" name="frmlogin" method="post" action="register.do">
                                    <input type="hidden" name="csrf_test_name">
                                    <div class="row type2 clearfix">
                                        <div class="col col1">
                                            <div class="form-group">
                                                <label class="control-label required">帳號</label>
                                                <div class="control-box">
                                                    <input class="form-control validate[required,custom[email]]"
                                                        type="text" name="account" id="signup_id" value=""
                                                        placeholder="帳號只能是英文和數字" tabindex="1" autocomplete="off">
                                                    <font color="red" size="-1">${MsgMap.errorIdEmpty}${MsgMap.errorIdDup}</font>    
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label required">密碼</label>
                                                <div class="control-box">
                                                    <input class="form-control validate[required]" type="password"
                                                        name="password" id="signup_password" value=""
                                                        placeholder="請輸入8碼以上的密碼" tabindex="2" autocomplete="off">
                                                    <font color="red" size="-1">${MsgMap.errorPasswordEmpty}${MsgMap.passwordError}</font>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label required">電子郵件</label>
                                                <div class="control-box">
                                                    <input class="form-control validate[required,custom[email]]"
                                                        type="text" name="email" id="signup_mail" value=""
                                                        placeholder="請輸入電子郵件" tabindex="3" autocomplete="off">
                                                    <font color="red" size="-1">${MsgMap.errorEmail}</font>
                                                </div>
                                            </div>
                                           
                                        </div>
                                        
                                        <div class="col col2">
                                            <button type="submit" class="btn normal2 send">Sign Up 註冊</button>
                                        </div>
                                        <div class="col col1">
                                            <span>已經有帳號了嗎?</span>
                                            <a href="member_register.html" class="btn pure">會員登入</a>
                                        </div>
                                    </div>
                                </form>

                            </div>

                        </div>
                    </section>
                </div>
            </div>
            <section class="sect3">
                <div class="container">
                    <ul class="cf-list list-h">
                    </ul>
                </div>
            </section>
        </main>

        <footer class="site-footer">
            <div class="footer-bottom">
                <div class="container">
                    <span class="copyright">Copyright © 第13期第二組 All Rights Reserved.</span>
                </div>
            </div>
        </footer>
    </div>
    <div class="floating top-hide" style="display: block;">
        <a href="#" class="ibtn gotop" title="TOP">TOP</a>
    </div>
    <span class="btn toggle-mmenu-cover toggleBtn" data-toggletag="mmenu-open"></span>
    <script defer="" src="/js/jquery.scrollTo.min.js"></script>
    <script defer="" src="/menu/page.js"></script>
    <script defer="" src="/js/basic.js"></script>
    <script defer="" src="/scripts/main.js"></script>
</body>
</html>