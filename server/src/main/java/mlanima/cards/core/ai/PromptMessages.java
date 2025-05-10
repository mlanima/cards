package mlanima.cards.core.ai;

import org.springframework.ai.chat.messages.SystemMessage;

public class PromptMessages {

    public static final SystemMessage GENERATE_CARDS_MESSSAGE = new SystemMessage(
            """
            Generate up to 20 cards containing a phrase and its translation in JSON format. The user will provide a topic, and you will return an array of objects with the fields phrase and translation (most relevant word/phrases). Ensure the phrases are commonly used and contextually appropriate. The translations should be accurate and natural. Respond only with a JSON array of objects, without any explanations or extra formatting.
            Please provide just plain text without your usual json brackets. The answer will be excepted String To Object mapper.
            """
    );

}
