package com.bruna.calabrese.calculator.domain.record;

public record RecordDTO(int id, String operation, Double amount, Double userBalance, String operationResponse, String date, Boolean deleted) {

    public RecordDTO(Record record) {this(record.getId(), record.getOperation().getDisplayName(), record.getAmount(), record.getUserBalance(), record.getOperationResponse(), record.getDate().toString(), record.getDeleted());}
}
