--------------------------------------------------------
--  File created - Sunday-May-31-2020   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Procedure TOTAL_EMP_BY_DEPT_ID
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "CJV805_202A10"."TOTAL_EMP_BY_DEPT_ID" (
	   p_depid     IN EMPLOYEES.DEPARTMENT_ID%TYPE,
	   o_numofemps OUT BINARY_INTEGER)
IS
BEGIN

  SELECT COUNT(EMPLOYEE_ID)
  INTO o_numofemps
  from  EMPLOYEES WHERE DEPARTMENT_ID = p_depid;

END;

/
