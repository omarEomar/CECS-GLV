@echo:
set /p moduleName=Enter Your Module Name:%=%
set /p branchName=Enter Branch Name or 'head':%=%
set BRANCH=%branchName%
call cvs-glv-config.bat moduleName

@echo:
pause

cd..
cd..

set MODULE_PAGES_FULL_PATH=%MODULE%\%MODULE_PAGES_PATH%

rmdir /s /q "Concerns\JSFBase"
rmdir /s /q "Concerns\JSFIntegration"
rmdir /s /q "Phase1\Work_Stream_A\Code\DevCode\01-GN\GRS\INTG-PRS"
rmdir /s /q "Phase1\Work_Stream_A\Code\DevCode\01-GN\INF\INTG-PRS"
rmdir /s /q "Phase1\Work_Stream_A\Code\DevCode\02-NL\JOB\INTG-PRS"
rmdir /s /q "Phase1\Work_Stream_A\Code\DevCode\02-NL\QUL\INTG-PRS"
rmdir /s /q "Phase1\Work_Stream_B\Code\DevCode\02-NL\REG\INTG-PRS"
rmdir /s /q "Phase1\Work_Stream_A\Code\DevCode\02-NL\ORG\INTG-PRS"
rmdir /s /q "Phase1\Work_Stream_A\Code\DevCode\02-NL\BNK\INTG-PRS"
rmdir /s /q "Phase1\Work_Stream_B\Code\DevCode\03-HR\TRN\INTG-PRS"
rmdir /s /q "Phase1\Work_Stream_B\Code\DevCode\03-HR\EMP\INTG-PRS"
rmdir /s /q "Phase1\Work_Stream_B\Code\DevCode\03-HR\BGT\INTG-PRS"
rmdir /s /q "Phase1\Work_Stream_B\Code\DevCode\03-HR\DED2\INTG-PRS"
rmdir /s /q "Phase1\Work_Stream_B\Code\DevCode\03-HR\SCP\INTG-PRS"
rmdir /s /q "Concerns\Reports"
rmdir /s /q "%MODULE%"


git init
git config core.sparsecheckout true
echo %MODULE_GIT%* >> .git/info/sparse-checkout
echo Concerns/JSFBase/* >> .git/info/sparse-checkout
echo Concerns/JSFIntegration/* >> .git/info/sparse-checkout
echo Phase1/Work_Stream_A/Code/DevCode/01-GN/GRS/INTG-PRS/* >> .git/info/sparse-checkout
echo Phase1/Work_Stream_A/Code/DevCode/01-GN/INF/INTG-PRS/* >> .git/info/sparse-checkout
echo Phase1/Work_Stream_A/Code/DevCode/02-NL/JOB/INTG-PRS/* >> .git/info/sparse-checkout
echo Phase1/Work_Stream_A/Code/DevCode/02-NL/QUL/INTG-PRS/* >> .git/info/sparse-checkout
echo Phase1/Work_Stream_B/Code/DevCode/02-NL/REG/INTG-PRS/* >> .git/info/sparse-checkout
echo Phase1/Work_Stream_A/Code/DevCode/02-NL/ORG/INTG-PRS/* >> .git/info/sparse-checkout
echo Phase1/Work_Stream_B/Code/DevCode/03-HR/TRN/INTG-PRS/* >> .git/info/sparse-checkout
echo Phase1/Work_Stream_B/Code/DevCode/02-NL/BNK/INTG-PRS/* >> .git/info/sparse-checkout
echo Phase1/Work_Stream_B/Code/DevCode/03-HR/MER/INTG-PRS/* >> .git/info/sparse-checkout
echo Phase1/Work_Stream_B/Code/DevCode/03-HR/EMP/INTG-PRS/* >> .git/info/sparse-checkout
echo Phase1/Work_Stream_B/Code/DevCode/03-HR/BGT/INTG-PRS/* >> .git/info/sparse-checkout
echo Phase1/Work_Stream_B/Code/DevCode/03-HR/DED2/INTG-PRS/* >> .git/info/sparse-checkout
echo Phase1/Work_Stream_B/Code/DevCode/03-HR/SCP/INTG-PRS/* >> .git/info/sparse-checkout
git remote add -f origin https://github.com/omarEomar/CECS-GLV.git
git pull origin master
mkdir %MODULE_PAGES_FULL_PATH%\app
pause
xcopy /s /e /y /i /h Concerns\JSFBase\public_html\csc\app %MODULE_PAGES_FULL_PATH%\app
pause
mkdir %MODULE_PAGES_FULL_PATH%\integration
xcopy /s /e /y /i /h Concerns\JSFBase\public_html\csc\integration  %MODULE_PAGES_FULL_PATH%\integration

mkdir %MODULE_PAGES_FULL_PATH%\security
xcopy /s /e /y /i /h Concerns\JSFBase\public_html\csc\security  %MODULE_PAGES_FULL_PATH%\security
xcopy /s /e /y /i /h Concerns\JSFBase\public_html\csc\errorpage.jsp  %MODULE_PAGES_FULL_PATH%
xcopy /s /e /y /i /h Concerns\JSFBase\public_html\csc\home.jsp  %MODULE_PAGES_FULL_PATH%
xcopy /s /e /y /i /h Concerns\JSFBase\public_html\csc\userGuide.jsp  %MODULE_PAGES_FULL_PATH%
mkdir %MODULE_PAGES_FULL_PATH%\WEB-INF\app
xcopy /s /e /y /i /h Concerns\JSFBase\public_html\csc\WEB-INF\app  %MODULE_PAGES_FULL_PATH%\WEB-INF\app
mkdir %MODULE_PAGES_FULL_PATH%\WEB-INF\lib
xcopy /s /e /y /i /h Concerns\JSFBase\public_html\csc\WEB-INF\lib  %MODULE_PAGES_FULL_PATH%\WEB-INF\lib
xcopy /s /e /y /i /h Concerns\JSFBase\public_html\csc\WEB-INF\web.xml  %MODULE_PAGES_FULL_PATH%\WEB-INF

mkdir %MODULE_PAGES_FULL_PATH%\module\grs
xcopy /s /e /y /i /h Concerns\JSFIntegration\JSFIntegration\public_html\pages\module\grs  %MODULE_PAGES_FULL_PATH%\module\grs

mkdir %MODULE_PAGES_FULL_PATH%\module\inf
xcopy /s /e /y /i /h Concerns\JSFIntegration\JSFIntegration\public_html\pages\module\inf  %MODULE_PAGES_FULL_PATH%\module\inf

mkdir %MODULE_PAGES_FULL_PATH%\module\map
xcopy /s /e /y /i /h Concerns\JSFIntegration\JSFIntegration\public_html\pages\module\map  %MODULE_PAGES_FULL_PATH%\module\map

mkdir %MODULE_PAGES_FULL_PATH%\module\reg
xcopy /s /e /y /i /h Concerns\JSFIntegration\JSFIntegration\public_html\pages\module\reg  %MODULE_PAGES_FULL_PATH%\module\reg

mkdir %MODULE_PAGES_FULL_PATH%\module\jsps\reports
xcopy /s /e /y /i /h Concerns\JSFBase\public_html\csc\module\jsps\reports  %MODULE_PAGES_FULL_PATH%\module\jsps\reports

mkdir %MODULE_PAGES_FULL_PATH%\module\jsps\grs
xcopy /s /e /y /i /h Concerns\JSFIntegration\JSFIntegration\public_html\pages\module\jsps\grs  %MODULE_PAGES_FULL_PATH%\module\jsps\grs

mkdir %MODULE_PAGES_FULL_PATH%\module\jsps\reg
xcopy /s /e /y /i /h Concerns\JSFIntegration\JSFIntegration\public_html\pages\module\jsps\reg  %MODULE_PAGES_FULL_PATH%\module\jsps\reg

mkdir %MODULE_PAGES_FULL_PATH%\module\jsps\shared\employeesearch
xcopy /s /e /y /i /h Concerns\JSFIntegration\JSFIntegration\public_html\pages\module\jsps\shared\employeesearch  %MODULE_PAGES_FULL_PATH%\module\jsps\shared\employeesearch

mkdir %MODULE_PAGES_FULL_PATH%\module\jsps\shared\governmentalemployeedatarevision
xcopy /s /e /y /i /h Concerns\JSFIntegration\JSFIntegration\public_html\pages\module\jsps\shared\governmentalemployeedatarevision  %MODULE_PAGES_FULL_PATH%\module\jsps\shared\governmentalemployeedatarevision

mkdir %MODULE_PAGES_FULL_PATH%\integration\grs
xcopy /s /e /y /i /h Phase1\Work_Stream_A\Code\DevCode\01-GN\GRS\INTG-PRS\src\com\beshara\csc\gn\grs\integration\presentation\pages\integration\grs  %MODULE_PAGES_FULL_PATH%\integration\grs

mkdir %MODULE_PAGES_FULL_PATH%\integration\inf
xcopy /s /e /y /i /h Phase1\Work_Stream_A\Code\DevCode\01-GN\INF\INTG-PRS\src\com\beshara\csc\gn\inf\integration\presentation\pages\integration\inf  %MODULE_PAGES_FULL_PATH%\integration\inf

mkdir %MODULE_PAGES_FULL_PATH%\integration\org
xcopy /s /e /y /i /h Phase1\Work_Stream_A\Code\DevCode\02-NL\ORG\INTG-PRS\src\com\beshara\csc\nl\org\integration\presentation\pages\integration\org  %MODULE_PAGES_FULL_PATH%\integration\org

mkdir %MODULE_PAGES_FULL_PATH%\integration\reg
xcopy /s /e /y /i /h Phase1\Work_Stream_B\Code\DevCode\02-NL\REG\INTG-PRS\src\com\beshara\csc\nl\reg\integration\presentation\pages\integration\reg  %MODULE_PAGES_FULL_PATH%\integration\reg

mkdir %MODULE_PAGES_FULL_PATH%\integration\job
xcopy /s /e /y /i /h Phase1\Work_Stream_A\Code\DevCode\02-NL\JOB\INTG-PRS\src\com\beshara\csc\nl\job\integration\presentation\pages\integration\job  %MODULE_PAGES_FULL_PATH%\integration\job

mkdir %MODULE_PAGES_FULL_PATH%\integration\qul
xcopy /s /e /y /i /h Phase1\Work_Stream_A\Code\DevCode\02-NL\QUL\INTG-PRS\src\com\beshara\csc\nl\qul\integration\presentation\pages\integration\qul  %MODULE_PAGES_FULL_PATH%\integration\qul

mkdir %MODULE_PAGES_FULL_PATH%\integration\trn
xcopy /s /e /y /i /h Phase1\Work_Stream_B\Code\DevCode\03-HR\TRN\INTG-PRS\src\com\beshara\csc\hr\trn\integration\presentation\pages\integration\trn  %MODULE_PAGES_FULL_PATH%\integration\trn

mkdir %MODULE_PAGES_FULL_PATH%\integration\bnk
xcopy /s /e /y /i /h Phase1\Work_Stream_B\Code\DevCode\02-NL\BNK\INTG-PRS\src\com\beshara\csc\nl\bnk\integration\presentation\pages\integration\bnk  %MODULE_PAGES_FULL_PATH%\integration\bnk

mkdir %MODULE_PAGES_FULL_PATH%\integration\mer
xcopy /s /e /y /i /h Phase1\Work_Stream_B\Code\DevCode\03-HR\MER\INTG-PRS\src\com\beshara\csc\hr\mer\integration\presentation\pages\integration\mer  %MODULE_PAGES_FULL_PATH%\integration\mer

mkdir %MODULE_PAGES_FULL_PATH%\integration\emp
xcopy /s /e /y /i /h Phase1\Work_Stream_B\Code\DevCode\03-HR\EMP\INTG-PRS\src\com\beshara\csc\hr\emp\integration\presentation\pages\integration\emp  %MODULE_PAGES_FULL_PATH%\integration\emp

mkdir %MODULE_PAGES_FULL_PATH%\integration\bgt
xcopy /s /e /y /i /h Phase1\Work_Stream_B\Code\DevCode\03-HR\BGT\INTG-PRS\src\com\beshara\csc\hr\bgt\integration\presentation\pages\integration\bgt  %MODULE_PAGES_FULL_PATH%\integration\bgt

mkdir %MODULE_PAGES_FULL_PATH%\integration\ded
xcopy /s /e /y /i /h Phase1\Work_Stream_B\Code\DevCode\03-HR\DED2\INTG-PRS\src\com\beshara\csc\hr\ded\integration\presentation\pages\integration\ded  %MODULE_PAGES_FULL_PATH%\integration\ded

mkdir %MODULE_PAGES_FULL_PATH%\integration\scp
xcopy /s /e /y /i /h Phase1\Work_Stream_B\Code\DevCode\03-HR\SCP\INTG-PRS\src\com\beshara\csc\hr\scp\integration\presentation\pages\integration\scp  %MODULE_PAGES_FULL_PATH%\integration\scp

	
rmdir /s /q %MODULE_PAGES_FULL_PATH%\WEB-INF\integration