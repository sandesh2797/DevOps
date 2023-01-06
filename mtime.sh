export heatsink=$HOSTNAME/forever/heatsink
env=Tagic
dir=/opt/log/temp_log
echo "creating temp_log directory"
mkdir $dir
# heatsink logs
echo "find log files and move into temp_log"
find /opt/log/forever/heatsink/ -type f -mtime +15 -exec mv {} $dir \;
echo "rename log files with date"
for i in $(ls /opt/log/temp_log/*);
do echo $i;
mv $i ${i}.`date +"%Y%m%d"`;
done
echo "gz log files"
gzip $dir/
azcopy --source $dir --destination $destination$env/$heatsink --dest-key $key --recursive                                                                                                                                                                                                                                                       rm -rf $dir/
