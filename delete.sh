export destination=https://logstoragebagic.blob.core.windows.net/logbackup/
export
export heatsink=$HOSTNAME/forever/heatsink
DATE=$(date -d '+%Y-%m-%d')
env=dev
dir=/opt/log/forever/heatsink
echo "creating log_temp directory"
#mkdir $dir
# heatsink logs
echo "find log file"
find /opt/log/forever/heatsink/
echo "gz log files"
gzip $dir/*

