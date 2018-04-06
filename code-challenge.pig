register /home/cloudera/Downloads/piggybank.jar

dataset1 = load 'user/workspace/33/ccds1.csv' using org.apache.pig.piggybank.storage.CSVExcelStorage() as (id:chararray, UA:chararray);

dataset1trans = FOREACH dataset1 GENERATE (INT) REPLACE(id, '\\"','') as id, UA;

dataset2 = load 'user/workspace/33/ccds2.tsv' using PigStorage('\t') as (id:INT, country:chararray, F1:INT, F2:INT, F3:INT);

joinedDataset = join dataset1trans by id, dataset2 by id;

OrderedDataset = foreach joinedDataset generate dataset1trans::id,country,UA,F1,F2,F3;

FilteredDataset = filter OrderedDataset by not dataset2::country  IN ('AT','BE','BG','HR','CY','CZ','DK','EE','FI','FR','DE','EL','HU','IE','IT','LV','LT','LU','MT','NL','PL','PT','RO','SK','SI','ES','SE','UK');

store FilteredDataset into 'user/workspace/33/resultfilter.txt' using PigStorage (',');

