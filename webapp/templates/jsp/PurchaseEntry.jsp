<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Purchase Entry</title>
</head>
<body>
    <div align="center">
        <form:form action="submit-purchase" method="post" modelAttribute="purchaseEntry">
            <table border="0">
                <tr>
                    <td colspan="2" align="center"><h2>Purchase Entry</h2></td>
                </tr>
                <tr>
                    <td>Enterprise Name:</td>
                    <td><form:input path="enterprise.enterpriseName" /></td>
                </tr>
                <tr>
                    <td>Bill No.:</td>
                    <td><form:input path="purchase.billNo" /></td>
                </tr>
                <tr>
                    <td><label for="start">Bill Date:</label></td>
                    <td><form:input type="date" id="start" name="bill-start" path="purchase.billDate"
                               min="2000-01-01"></td>
                </tr>
                <tr>
                    <td>Amount:</td>
                    <td><form:input type="number" step=0.01 path="purchase.amount" /></td>
                </tr>
                <tr>
                    <td>VAT:</td>
                    <td><form:input type="number" step=0.01 path="purchase.amountVat" /></td>
                </tr>
                <tr>
                    <td colspan="2" align="center"><input type="submit" value="Submit" /></td>
                </tr>
            </table>
        </form:form>
    </div>
</body>
</html>