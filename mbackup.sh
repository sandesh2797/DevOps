mongo_truncation ()
{
DATE=$( date -d '-60 day' '+%Y-%m-%d' )
mkdir -p /mongo/backup/mongo-data
dir=/mongo/backup/mongo-data
host=$1                                                                                                                                                                                                               port=$2
result=`mongo --host $host --port $port --eval 'var currentTimestamp =  new Date();                                                                                                                                                                                                                                                      var startTimestamp = new Date (currentTimestamp); startTimestamp.setDate (currentTimestamp.getDate()- 60);                                                                                                                                                      var startObjectId = new ObjectId(Math.floor(startTimestamp.getTime()/1000).toString(16) + "0000000000000000");
                                         print(startObjectId);'

startobjectid=$(echo ${result##* });
final=$(echo ${startobjectid##* });
echo $final
mongodump_query='{"_id" : {"$lt" : '$final'}}';
cd "$dir"
mongodump \
--host $host \
--port $port \
--db retainer \
--query "$mongodump_query"

tar -zcvf ${DATE}-mongo-dump.tgz dump
if [ $? == 0 ] ; then
 echo 'successfully back-up the mongo data '
else
echo 'failed the backup'
fi

rm -Rf dump

azcopy --source /mongo/backup/mon.core.winfmUcExw== --recursive

rm -rf ${DATE}-mongo-dump.tgz


}
