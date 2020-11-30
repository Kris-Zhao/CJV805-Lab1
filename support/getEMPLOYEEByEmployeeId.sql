--------------------------------------------------------
--  File created - Sunday-May-31-2020   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Procedure GETEMPLOYEEBYEMPLOYEEID
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "CJV805_202A10"."GETEMPLOYEEBYEMPLOYEEID" (
	   p_empid     IN EMPLOYEES.EMPLOYEE_ID%TYPE,
	   o_firstname OUT EMPLOYEES.FIRST_NAME%TYPE,
	   o_lastname  OUT  EMPLOYEES.LAST_NAME%TYPE,
	   o_email  OUT  EMPLOYEES.EMAIL%TYPE,
	   o_salary    OUT  EMPLOYEES.SALARY%TYPE)
IS
BEGIN

  SELECT FIRST_NAME , LAST_NAME, EMAIL, SALARY
  INTO o_firstname, o_lastname,  o_email, o_salary
  from  EMPLOYEES WHERE EMPLOYEE_ID = p_empid;

END;

/
