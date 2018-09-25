rem
@echo off

cls
color a
@echo [Galaxy Install V2.0 tools]
Title [Galaxy Install V2.0 tools]
::���������ʼ-----------------------------------

@echo off
echo ************************************************************
echo **                                                        **
echo **            Galaxy Install For Mysql                    **
echo **                                                        **
echo **               ����ƽ̨�ű�����                         **
echo **                                                        **
echo ************************************************************
set  GALAXY_PATH=%CD%
echo current path is %GALAXY_PATH%
echo ������Galaxy��װ�����ݿ��IP��
set /p HOST=
echo ������Galaxy��װ�����ݿ�Ķ˿�PORT��
set /p PORT=
echo ������ָ�������ݿ��û�����
set /p DB_USER=
echo ������%DB_USER%�����룺
set /p DB_PWD=
echo ��������Ҫ���������ݿ�����
set /p DB_NAME=

echo ------------------------------------------------------------
echo ������ϲ��������⣬��Ctrl+C��ֹ�������������⣬�밴�����������
pause

echo ***********************************************************************************
echo Install Galaxyrem 
cd %GALAXY_PATH%\


echo Galaxy_Install start... ...
rem ɾ��ָ�����ݿ�
mysqladmin -u%DB_USER% -p%DB_PWD% -P %PORT% -h %HOST% drop %DB_NAME%
rem ����ָ�����ݿ�
mysqladmin -u%DB_USER% -p%DB_PWD% -P %PORT% -h %HOST% create %DB_NAME%
rem ����ҵ���
mysql -u%DB_USER% -p%DB_PWD% -P %PORT% -h %HOST% -D %DB_NAME% <%GALAXY_PATH%\business\fw_reversal_tran_mysql.sql 
mysql -u%DB_USER% -p%DB_PWD% -P %PORT% -h %HOST% -D %DB_NAME% <%GALAXY_PATH%\business\fw_tran_info_mysql.sql 
mysql -u%DB_USER% -p%DB_PWD% -P %PORT% -h %HOST% -D %DB_NAME% <%GALAXY_PATH%\business\fw_sequences_mysql.sql 
rem �����������
mysql -u%DB_USER% -p%DB_PWD% -P %PORT% -h %HOST% -D %DB_NAME% <%GALAXY_PATH%\business-batch\batch_mysql.sql 
mysql -u%DB_USER% -p%DB_PWD% -P %PORT% -h %HOST% -D %DB_NAME% <%GALAXY_PATH%\business-batch\batch_param_mysql.sql 
rem ����������ݳ�ʼ��
mysql -u%DB_USER% -p%DB_PWD% -P %PORT% -h %HOST% -D %DB_NAME% <%GALAXY_PATH%\business-batch\batch_init.sql 
rem ����Stira��
mysql -u%DB_USER% -p%DB_PWD% -P %PORT% -h %HOST% -D %DB_NAME% <%GALAXY_PATH%\business-stria\stria_mysql.sql 
rem ����dtp��
mysql -u%DB_USER% -p%DB_PWD% -P %PORT% -h %HOST% -D %DB_NAME% <%GALAXY_PATH%\dtp\dtp_mysql.sql 
rem ����dal��
mysql -u%DB_USER% -p%DB_PWD% -P %PORT% -h %HOST% -D %DB_NAME% <%GALAXY_PATH%\dal\dal_mysql.sql

rem ������ʱ�����
mysql -u%DB_USER% -p%DB_PWD% -P %PORT% -h %HOST% -D %DB_NAME% <%GALAXY_PATH%\business-batch\batch_timer_mysql.sql
rem ����������һ���Ա�
mysql -u%DB_USER% -p%DB_PWD% -P %PORT% -h %HOST% -D %DB_NAME% <%GALAXY_PATH%\business-batch\batch_to_commit_mysql.sql
echo ***********************************************************************************

echo Galaxy_Install finished

echo ***********************************************************************************

pause

