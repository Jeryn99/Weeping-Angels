# Define the path to the folder you want to clear
$folderPath = "C:\Users\Craig\IdeaProjects\Weeping-Angels\.gradle"

# Ensure the folder exists
if (Test-Path $folderPath) {
    try {
        # Get all files and folders in the specified folder
        $items = Get-ChildItem -Path $folderPath -Recurse

        # Iterate over each item and attempt to remove it
        foreach ($item in $items) {
            try {
                Remove-Item -Path $item.FullName -Recurse -Force -ErrorAction Stop
            } catch {
                Write-Host "Failed to delete $($item.FullName): $($_.Exception.Message)"
            }
        }

        # Optionally remove the folder itself
        # Remove-Item -Path $folderPath -Recurse -Force

        Write-Host "All items in $folderPath have been deleted."
    } catch {
        Write-Host "An error occurred: $($_.Exception.Message)"
    }
} else {
    Write-Host "The specified folder does not exist."
}
