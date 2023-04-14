package br.com.component.extension.enums;

public enum AutoGenerateStrategy {

    /**
     * Instructs to always generate both on create and update.
     */
    PartitionKey,

    /**
     * Instructs to generate on create only.
     */
    ComposeKey;
}
