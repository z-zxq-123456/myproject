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
echo **            Galaxy Install For Oracle                   **
echo **                                                        **
echo **                技术平台脚本创建                        **
echo **                                                        **
echo ************************************************************
set  GALAXY_PATH=%CD%
echo current path is %GALAXY_PATH%
echo 请输入Galaxy安装的数据库的IP,端口及SID(IP:PORT/SID)：
set /p HOST=
echo 请输入指定的数据库用户名：
set /p DB_USER=
echo 请输入%DB_USER%的密码：
set /p DB_PWD=

echo ------------------------------------------------------------
echo 如果以上参数有问题，请Ctrl+C终止操作，若无问题，请按任意键继续。
pause

echo ***********************************************************************************
echo Install Galaxy:
cd %GALAXY_PATH%\


echo Galaxy_Install start... ...
sqlplus   %DB_USER%/%DB_PWD%@%HOST%  @./Galaxy_Install_For_Oracle.sql %DB_USER% >"%GALAXY_PATH%\Galaxy_Table_Install.log"
echo Galaxy_Install finished

echo ***********************************************************************************
notepad "%GALAXY_PATH%\Galaxy_Table_Install.log"
cd %GALAXY_PATH%\
echo have done!
endlocal
echo ------------------------------------------------------------
echo 安装完毕，详情请到“%GALAXY_PATH%”目录下查看日志。
pause

