@echo off
setlocal enabledelayedexpansion
cd %~dp0

set BIN="D:\javaken\mysql-5.7.21-winx64\bin\mysqladmin"
set PASSWORD=******

%BIN% --user=root --password=%PASSWORD% shutdown

