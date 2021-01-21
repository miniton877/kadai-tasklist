<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<label for="context">タスク</label><br />
<input type="text" name="context" value="${task.content}" /><br /><br />

<input type="hidden" name="_token" value="${_token}" />
<button type="submit">提出</button>