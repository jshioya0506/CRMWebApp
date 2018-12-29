@echo off
setlocal enabledelayedexpansion
cd %~dp0

set BIN="D:\javaken\mysql-5.7.21-winx64\bin\mysqld.exe"
set INI="D:\javaken\mysql-5.7.21-winx64\my.ini"

start "MySQL_Server" %BIN% --defaults-file=%INI%

