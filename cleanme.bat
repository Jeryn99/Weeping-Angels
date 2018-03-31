@ECHO OFF
IF NOT EXIST formatter/ (
	echo Formatter not found, downloading...
	powershell -Command "Invoke-WebRequest http://download1941.mediafire.com/44352k91t6cg/mb88ia9mh4b5wps/atg-formatter.zip -OutFile atg-formatter.zip"
	echo Download successful
	echo Extracting...
	powershell -Command "& { Add-Type -A 'System.IO.Compression.FileSystem'; [IO.Compression.ZipFile]::ExtractToDirectory('atg-formatter.zip', 'formatter'); }"
	echo Extraction complete, cleaning up
	del atg-formatter.zip
)

java -jar formatter/plugins/org.eclipse.osgi_3.11.2.v20161107-1947.jar -application org.eclipse.jdt.core.JavaCodeFormatter -config formatter/format-settings.ini src/*
rmdir workspace /s /q
