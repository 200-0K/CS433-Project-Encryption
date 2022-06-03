$size=2097152
$files=2500
$output="test\$files"
New-Item -Path $output -ItemType Directory -Force
for ($i = 0; $i -lt $files; $i++) 
{
  & fsutil file createnew $output\dummy$i $size 
}