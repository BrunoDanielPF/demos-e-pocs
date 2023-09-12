package com.example.dynamodb.demodynamooperations.operations.index;

import com.example.dynamodb.demodynamooperations.beans.MessageThread;
import software.amazon.awssdk.core.pagination.sync.SdkIterable;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbIndex;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.model.Page;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GlobalIndexOperationDynamo {

    public static List<MessageThread> queryUsingSecondaryIndices(DynamoDbTable<MessageThread> threadTable) {

//        QueryConditional queryConditional = QueryConditional.keyEqualTo(qc ->
//                qc.partitionValue("Forum02"));
//
//        final DynamoDbIndex<MessageThread> forumLastPostedDateIndex = threadTable.index("SubjectLastPostedDateIndex");
//
//        final SdkIterable<Page<MessageThread>> pagedResult = forumLastPostedDateIndex.query(q -> q
//                .queryConditional(queryConditional)
//                // 5. Request three attribute in the results.
//                .attributesToProject("forumName", "subject", "lastPostedDateTime"));
//
//        List<MessageThread> collectedItems = new ArrayList<>();
//        pagedResult.stream().forEach(page -> page.items().stream()
//                .sorted(Comparator.comparing(MessageThread::getLastPostedDateTime))
//                .forEach(collectedItems::add));
//        return collectedItems;
        return null;
    }
}
