<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false"%>
<%@ page import="java.util.*, java.text.*" %>

<c:set var="lang" value="${not empty param.lang ? param.lang : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${lang}" />
<fmt:setBundle basename="messages" />

<html lang="${cookie['lang'].value}">

<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta content="ie=edge" http-equiv="x-ua-compatible" />
    <title>Periodicals</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" crossorigin="anonymous"></script>
    <script src="http://code.jquery.com/jquery-1.8.3.js"></script>
</head>
<body>