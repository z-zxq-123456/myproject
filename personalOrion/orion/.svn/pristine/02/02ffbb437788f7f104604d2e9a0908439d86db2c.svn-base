rem
@echo off

cls
color a
@echo [Galaxy Install V2.0 tools]
Title [Galaxy Install V2.0 tools]
::批处理命令开始-----------------------------------

@echo off
echo ************************************************************
echo **                                                        **
echo **            Galaxy Install For Mysql                    **
echo **                                                        **
echo **               技术平台脚本创建                         **
echo **                                                        **
echo ************************************************************
set  GALAXY_PATH=%CD%
echo current path is %GALAXY_PATH%
echo 请输入Galaxy安装的数据库的IP：
set /p HOST=
echo 请输入Galaxy安装的数据库的端口PORT：
set /p PORT=
echo 请输入指定的数据库用户名：
set /p DB_USER=
echo 请输入%DB_USER%的密码：
set /p DB_PWD=
echo 请输入需要创建的数据库名：
set /p DB_NAME=

echo ------------------------------------------------------------
echo 如果以上参数有问题，请Ctrl+C终止操作，若无问题，请按任意键继续。
pause

echo ***********************************************************************************
echo Install Galaxyrem 
cd %GALAXY_PATH%\


echo Galaxy_Install start... ...
rem 删除指定数据库
mysqladmin -u%DB_USER% -p%DB_PWD% -P %PORT% -h %HOST% drop %DB_NAME%
rem 创建指定数据库
mysqladmin -u%DB_USER% -p%DB_PWD% -P %PORT% -h %HOST% create %DB_NAME%
rem 创建业务表
mysql -u%DB_USER% -p%DB_PWD% -P %PORT% -h %HOST% -D %DB_NAME% <%GALAXY_PATH%\business\fw_reversal_tran_mysql.sql 
mysql -u%DB_USER% -p%DB_PWD% -P %PORT% -h %HOST% -D %DB_NAME% <%GALAXY_PATH%\business\fw_tran_info_mysql.sql 
mysql -u%DB_USER% -p%DB_PWD% -P %PORT% -h %HOST% -D %DB_NAME% <%GALAXY_PATH%\business\fw_sequences_mysql.sql 
rem 创建批处理表
mysql -u%DB_USER% -p%DB_PWD% -P %PORT% -h %HOST% -D %DB_NAME% <%GALAXY_PATH%\business-batch\batch_mysql.sql 
mysql -u%DB_USER% -p%DB_PWD% -P %PORT% -h %HOST% -D %DB_NAME% <%GALAXY_PATH%\business-batch\batch_param_mysql.sql 
rem 批处理表数据初始化
mysql -u%DB_USER% -p%DB_PWD% -P %PORT% -h %HOST% -D %DB_NAME% <%GALAXY_PATH%\business-batch\batch_init.sql 
rem 创建Stira表
mysql -u%DB_USER% -p%DB_PWD% -P %PORT% -h %HOST% -D %DB_NAME% <%GALAXY_PATH%\business-stria\stria_mysql.sql 
rem 创建dtp表
mysql -u%DB_USER% -p%DB_PWD% -P %PORT% -h %HOST% -D %DB_NAME% <%GALAXY_PATH%\dtp\dtp_mysql.sql 
rem 创建dal表
mysql -u%DB_USER% -p%DB_PWD% -P %PORT% -h %HOST% -D %DB_NAME% <%GALAXY_PATH%\dal\dal_mysql.sql

rem 创建定时任务表
mysql -u%DB_USER% -p%DB_PWD% -P %PORT% -h %HOST% -D %DB_NAME% <%GALAXY_PATH%\business-batch\batch_timer_mysql.sql
rem 创建批处理一致性表
mysql -u%DB_USER% -p%DB_PWD% -P %PORT% -h %HOST% -D %DB_NAME% <%GALAXY_PATH%\business-batch\batch_to_commit_mysql.sql
echo ***********************************************************************************

echo Galaxy_Install finished

echo ***********************************************************************************

pause

