set MODULE_PAGES_PATH=

goto %moduleName%

REM ###### Concerns #####################################################
:base
set MODULE=Concerns/Base
goto endConfig
:jsfbase
set MODULE=Concerns/JSFBase
goto endConfig
:jsfintg
set MODULE=Concerns/JSFIntegration
goto endConfig
:utils
set MODULE=Concerns/SharedUtils
goto endConfig

REM ###### Phase1 # Work_Stream_A # GN ##################################
:grs
set MODULE=Phase1\Work_Stream_A\Code\DevCode\01-GN\GRS
set MODULE_GIT=Phase1/Work_Stream_A/Code/DevCode/01-GN/GRS
set MODULE_PAGES_PATH=Model\src\com\beshara\csc\gn\grs\presentation\pages
goto endConfig
:inf
set MODULE=Phase1\Work_Stream_A\Code\DevCode\01-GN\INF
set MODULE_GIT=Phase1/Work_Stream_A/Code/DevCode/01-GN/INF
set MODULE_PAGES_PATH=Model\src\com\beshara\csc\inf\presentation\pages
goto endConfig
:map
set MODULE=Phase1\Work_Stream_A\Code\DevCode\01-GN\MAP
set MODULE_GIT=Phase1/Work_Stream_A/Code/DevCode/01-GN/MAP/
set MODULE_PAGES_PATH=Model\src\com\beshara\csc\gn\map\presentation\pages
goto endConfig
REM ###### Phase1 # Work_Stream_A # NL ##################################
:job
set MODULE=Phase1\Work_Stream_A\Code\DevCode\02-NL\JOB
set MODULE_GIT=Phase1/Work_Stream_A/Code/DevCode/02-NL/JOB
set MODULE_PAGES_PATH=Model\src\com\beshara\csc\nl\job\presentation\pages
goto endConfig
:org
set MODULE=Phase1\Work_Stream_A\Code\DevCode\02-NL\ORG
set MODULE_GIT=Phase1/Work_Stream_A/Code/DevCode/02-NL/ORG
set MODULE_PAGES_PATH=Model\src\com\beshara\csc\nl\org\presentation\pages
goto endConfig
:qul
set MODULE=Phase1\Work_Stream_A\Code\DevCode\02-NL\QUL
set MODULE=Phase1/Work_Stream_A/Code/DevCode/02-NL/QUL
set MODULE_PAGES_PATH=Model\src\com\beshara\csc\nl\qul\presentation\pages
goto endConfig
:rega
set MODULE=Phase1\Work_Stream_A\Code\DevCode\02-NL\REG
set MODULE_GIT=Phase1/Work_Stream_A/Code/DevCode/02-NL/REG
set MODULE_PAGES_PATH=Model\src\com\beshara\csc\nl\reg\presentation\pages
goto endConfig
REM ###### Phase1 # Work_Stream_A # HR ##################################
:fil
set MODULE=Phase1\Work_Stream_A\Code\DevCode\03-HR\FIL
set MODULE_GIT=Phase1/Work_Stream_A/Code/DevCode/03-HR/FIL
set MODULE_PAGES_PATH=Model\src\com\beshara\csc\hr\fil\presentation\pages
goto endConfig
REM ###### Phase1 # Work_Stream_B # GN ##################################
:aud
set MODULE=Phase1\Work_Stream_C\Code\DevCode\01-GN\AUD
set MODULE_GIT=Phase1/Work_Stream_C/Code/DevCode/01-GN/AUD
set MODULE_PAGES_PATH=Model\src\com\beshara\csc\gn\aud\presentation\pages
goto endConfig
:fut
set MODULE=Phase1\Work_Stream_B\Code\DevCode\01-GN\FUT
set MODULE_GIT=Phase1/Work_Stream_B/Code/DevCode/01-GN/FUT
set MODULE_PAGES_PATH=Model\src\com\beshara\csc\gn\fut\presentation\pages
goto endConfig
:req
set MODULE=Phase1\Work_Stream_B\Code\DevCode\01-GN\REQ
set MODULE_GIT=Phase1/Work_Stream_B/Code/DevCode/01-GN/REQ
set MODULE_PAGES_PATH=Model\src\com\beshara\csc\gn\req\presentation\pages
goto endConfig
:sap
set MODULE=Phase1\Work_Stream_B\Code\DevCode\01-GN\SAP
set MODULE_GIT=Phase1/Work_Stream_B/Code/DevCode/01-GN/SAP
set MODULE_PAGES_PATH=Model\src\com\beshara\csc\sap\presentation\pages
goto endConfig
REM ###### Phase1 # Work_Stream_B # NL ##################################
:reg
set MODULE=Phase1\Work_Stream_B\Code\DevCode\02-NL\REG
set MODULE_GIT=Phase1/Work_Stream_B/Code/DevCode/02-NL/REG
set MODULE_PAGES_PATH=Model\src\com\beshara\csc\nl\reg\presentation\pages
goto endConfig
:srv
set MODULE=Phase1\Work_Stream_B\Code\DevCode\02-NL\SRV
set MODULE_GIT=Phase1/Work_Stream_B/Code/DevCode/02-NL/SRV
set MODULE_PAGES_PATH=Model\src\com\beshara\csc\nl\srv\presentation\pages
goto endConfig
:bnk
set MODULE=Phase1\Work_Stream_B\Code\DevCode\02-NL\BNK
set MODULE_GIT=Phase1/Work_Stream_B/Code/DevCode/02-NL/BNK
set MODULE_PAGES_PATH=Model\src\com\beshara\csc\nl\bnk\presentation\pages
goto endConfig
:bnk_ext
set MODULE=Phase1\Work_Stream_B\Code\DevCode\02-NL\BNK_EXT
set MODULE_GIT=Phase1/Work_Stream_B/Code/DevCode/02-NL/BNK_EXT
set MODULE_PAGES_PATH=Model\src\com\beshara\csc\nl\bnk_ext\presentation\pages
goto endConfig
:oldbnk
set MODULE=Phase1\Work_Stream_B\Code\DevCode\02-NL\OldBNK
set MODULE_GIT=Phase1/Work_Stream_B/Code/DevCode/02-NL/OldBNK
goto endConfig
REM ###### Phase1 # Work_Stream_B # HR ##################################
:apr
set MODULE=Phase1\Work_Stream_B\Code\DevCode\03-HR\APR
set MODULE_GIT=Phase1/Work_Stream_B/Code/DevCode/03-HR/APR
set MODULE_PAGES_PATH=Model\src\com\beshara\csc\hr\apr\presentation\pages
goto endConfig
:att
set MODULE=Phase1\Work_Stream_B\Code\DevCode\03-HR\ATT
set MODULE_GIT=Phase1/Work_Stream_B/Code/DevCode/03-HR/ATT
set MODULE_PAGES_PATH=Model\src\com\beshara\csc\hr\att\presentation\pages
goto endConfig
:bgt
set MODULE=Phase1\Work_Stream_B\Code\DevCode\03-HR\BGT
set MODULE_GIT=Phase1/Work_Stream_B/Code/DevCode/03-HR/BGT
set MODULE_PAGES_PATH=Model\src\com\beshara\csc\hr\bgt\presentation\pages
goto endConfig
:cer
set MODULE=Phase1\Work_Stream_B\Code\DevCode\03-HR\CER
set MODULE_GIT=Phase1/Work_Stream_B/Code/DevCode/03-HR/CER
set MODULE_PAGES_PATH=Model\src\com\beshara\csc\hr\cer\presentation\pages
goto endConfig
:crs
set MODULE=Phase1\Work_Stream_B\Code\DevCode\03-HR\CRS
set MODULE_GIT=Phase1/Work_Stream_B/Code/DevCode/03-HR/CRS
set MODULE_PAGES_PATH=Model\src\com\beshara\csc\hr\crs\presentation\pages
goto endConfig
:ded
set MODULE=Phase1\Work_Stream_B\Code\DevCode\03-HR\DED
set MODULE_GIT=Phase1/Work_Stream_B/Code/DevCode/03-HR/DED
set MODULE_PAGES_PATH=Model\src\com\beshara\csc\hr\ded\presentation\pages
goto endConfig
:ded2
set MODULE=Phase1\Work_Stream_B\Code\DevCode\03-HR\DED2
set MODULE_GIT=Phase1/Work_Stream_B/Code/DevCode/03-HR/DED2
set MODULE_PAGES_PATH=ViewController\public_html
goto endConfig
:emp
set MODULE=Phase1\Work_Stream_B\Code\DevCode\03-HR\EMP
set MODULE_GIT=Phase1/Work_Stream_B/Code/DevCode/03-HR/EMP
set MODULE_PAGES_PATH=Model\src\com\beshara\csc\hr\emp\presentation\pages
goto endConfig
:eos
set MODULE=Phase1\Work_Stream_B\Code\DevCode\03-HR\EOS
set MODULE_GIT=Phase1/Work_Stream_B/Code/DevCode/03-HR/EOS
set MODULE_PAGES_PATH=Model\src\com\beshara\csc\hr\eos\presentation\pages
goto endConfig
:inv
set MODULE=Phase1\Work_Stream_B\Code\DevCode\03-HR\INV
set MODULE_GIT=Phase1/Work_Stream_B/Code/DevCode/03-HR/INV
set MODULE_PAGES_PATH=Model\src\com\beshara\csc\hr\inv\presentation\pages
goto endConfig
:mer
set MODULE=Phase1\Work_Stream_B\Code\DevCode\03-HR\MER
set MODULE_GIT=Phase1/Work_Stream_B/Code/DevCode/03-HR/MER
set MODULE_PAGES_PATH=Model\src\com\beshara\csc\hr\mer\presentation\pages
goto endConfig
:mov
set MODULE=Phase1\Work_Stream_B\Code\DevCode\03-HR\MOV
set MODULE_GIT=Phase1/Work_Stream_B/Code/DevCode/03-HR/MOV
set MODULE_PAGES_PATH=Model\src\com\beshara\csc\hr\mov\presentation\pages
goto endConfig
:prm
set MODULE=Phase1\Work_Stream_B\Code\DevCode\03-HR\PRM
set MODULE_GIT=Phase1/Work_Stream_B/Code/DevCode/03-HR/PRM
set MODULE_PAGES_PATH=Model\src\com\beshara\csc\hr\prm\presentation\pages
goto endConfig
:sal
set MODULE=Phase1\Work_Stream_B\Code\DevCode\03-HR\SAL
set MODULE_GIT=Phase1/Work_Stream_B/Code/DevCode/03-HR/SAL
set MODULE_PAGES_PATH=Model\src\com\beshara\csc\hr\sal\presentation\pages
goto endConfig
:sal2
set MODULE=Phase1\Work_Stream_B\Code\DevCode\03-HR\SALV2
set MODULE_GIT=Phase1/Work_Stream_B/Code/DevCode/03-HR/SALV2
goto endConfig
:scp
set MODULE=Phase1\Work_Stream_B\Code\DevCode\03-HR\SCP
set MODULE_GIT=Phase1/Work_Stream_B/Code/DevCode/03-HR/SCP
set MODULE_PAGES_PATH=Model\src\com\beshara\csc\hr\scp\presentation\pages
goto endConfig
:set
set MODULE=Phase1\Work_Stream_B\Code\DevCode\03-HR\SET
set MODULE_GIT=Phase1/Work_Stream_B/Code/DevCode/03-HR/SET
set MODULE_PAGES_PATH=Model\src\com\beshara\csc\hr\set\presentation\pages
goto endConfig
:trn
set MODULE=Phase1\Work_Stream_C\Code\DevCode\03-HR\TRN
set MODULE_GIT=Phase1/Work_Stream_C/Code/DevCode/03-HR/TRN
set MODULE_PAGES_PATH=Model\src\com\beshara\csc\hr\trn\presentation\pages
goto endConfig
:vac
set MODULE=Phase1\Work_Stream_B\Code\DevCode\03-HR\VAC
set MODULE_GIT=Phase1/Work_Stream_B/Code/DevCode/03-HR/VAC
set MODULE_PAGES_PATH=Model\src\com\beshara\csc\hr\vac\presentation\pages
goto endConfig
:sec
set MODULE=Phase1\Work_Stream_B\Code\DevCode\01-GN\SEC
set MODULE_GIT=Phase1/Work_Stream_B/Code/DevCode/01-GN/SEC
set MODULE_PAGES_PATH=Model\src\com\beshara\csc\gn\sec\presentation\pages
goto endConfig
:flm
set MODULE=Concerns\FLM
set MODULE_GIT=Concerns/FLM
goto endConfig
REM ###### Phase1 # Work_Stream_C # GN ##################################

REM ###### Phase1 # Work_Stream_C # NL ##################################

REM ###### Phase1 # Work_Stream_C # HR ##################################
:mis
set MODULE=Phase1\Work_Stream_C\Code\DevCode\03-HR\MIS
set MODULE_GIT=Phase1/Work_Stream_C/Code/DevCode/03-HR/MIS
set MODULE_PAGES_PATH=Model\src\com\beshara\csc\hr\mis\presentation\pages
goto endConfig

:aoe
set MODULE=Phase1\Work_Stream_C\Code\DevCode\03-HR\AOE
set MODULE_GIT=Phase1/Work_Stream_C/Code/DevCode/03-HR/AOE
set MODULE_PAGES_PATH=Model\src\com\beshara\csc\hr\aoe\presentation\pages
goto endConfig

:bud
set MODULE=Phase1\Work_Stream_C\Code\DevCode\03-HR\BUD
set MODULE_GIT=Phase1/Work_Stream_C/Code/DevCode/03-HR/BUD
set MODULE_PAGES_PATH=Model\src\com\beshara\csc\hr\bud\presentation\pages
goto endConfig

:adc
set MODULE=Phase1\Work_Stream_C\Code\DevCode\03-HR\ADC
set MODULE_GIT=Phase1/Work_Stream_C/Code/DevCode/03-HR/ADC
set MODULE_PAGES_PATH=Model\src\com\beshara\csc\hr\adc\presentation\pages
goto endConfig 

:ftq
set MODULE=Phase1\Work_Stream_C\Code\DevCode\03-HR\FTQ
set MODULE_GIT=Phase1/Work_Stream_C/Code/DevCode/03-HR/FTQ
set MODULE_PAGES_PATH=ViewController\src\com\beshara\csc\hr\ftq\presentation\pages
goto endConfig 

:ldr
set MODULE=Phase1\Work_Stream_C\Code\DevCode\03-HR\LDR
set MODULE=Phase1/Work_Stream_C/Code/DevCode/03-HR/LDR
set MODULE_PAGES_PATH=Model\src\com\beshara\csc\hr\ldr\presentation\pages
goto endConfig

:puc
set MODULE=Phase1\Work_Stream_C\Code\DevCode\03-HR\PUC
set MODULE=Phase1/Work_Stream_C/Code/DevCode/03-HR/PUC
set MODULE_PAGES_PATH=Model\src\com\beshara\csc\hr\puc\presentation\pages
goto endConfig

:sup
set MODULE=Phase1\Work_Stream_C\Code\DevCode\01-GN\SUP
set MODULE=Phase1/Work_Stream_C/Code/DevCode/01-GN/SUP
set MODULE_PAGES_PATH=Model\src\com\beshara\csc\gn\sup\presentation\pages
goto endConfig

:dedintg
set MODULE=Phase1\Work_Stream_B\Code\DevCode\03-HR\DEDIntg
set MODULE_GIT=Phase1/Work_Stream_B/Code/DevCode/03-HR/DEDIntg
goto endConfig

:fdb
set MODULE=Phase1\Work_Stream_C\Code\DevCode\03-HR\FDB
set MODULE_GIT=Phase1/Work_Stream_C/Code/DevCode/03-HR/FDB
set MODULE_PAGES_PATH=Model\src\com\beshara\csc\hr\fdb\presentation\pages
goto endConfig

:cof
set MODULE=Phase1\Work_Stream_C\Code\DevCode\03-HR\COF
set MODULE_GIT=Phase1/Work_Stream_C/Code/DevCode/03-HR/COF
set MODULE_PAGES_PATH=Model\src\com\beshara\csc\hr\cof\presentation\pages
goto endConfig 

:rts
set MODULE=Phase1\Work_Stream_C\Code\DevCode\03-HR\RTS
set MODULE_GIT=Phase1/Work_Stream_C/Code/DevCode/03-HR/RTS
set MODULE_PAGES_PATH=Model\src\com\beshara\csc\hr\rts\presentation\pages
goto endConfig 
:clm
set MODULE=Phase1\Work_Stream_C\Code\DevCode\03-HR\CLM
set MODULE_GIT=Phase1/Work_Stream_C/Code/DevCode/03-HR/CLM
set MODULE_PAGES_PATH=Model\src\com\beshara\csc\hr\clm\presentation\pages
goto endConfig  
:endConfig