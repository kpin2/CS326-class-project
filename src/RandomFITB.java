import java.util.Random;

public class RandomFITB {

    private String questionText;
    private String sequence;
    private String correctAnswer;
    private String wrongAnswer1;
    private String wrongAnswer2;
    private String[] answers;
    private int correctAnswerIndex;

    Random rand = new Random();

    public RandomFITB() {

        questionText = "What numbers are next?";

        // Choose a random sequence of 3 numbers
        int sequenceStart = rand.nextInt(16);
        int correctAnswerStart = sequenceStart + 3;

        // Choose 2 random wrong sequences and run loops to ensure they're unique
        int wrongAnswer1Start;
        int wrongAnswer2Start;
        while (true) {
            int answer = rand.nextInt(16);
            if (answer != correctAnswerStart) {
                wrongAnswer1Start = answer;
                break;
            }
        }
        while (true) {
            int answer = rand.nextInt(16);
            if (answer != correctAnswerStart && answer != wrongAnswer1Start) {
                wrongAnswer2Start = answer;
                break;
            }
        }

        // Initialize empty strings
        sequence = "";
        correctAnswer = "";
        wrongAnswer1 = "";
        wrongAnswer2 = "";

        for (int i = 0; i < 3; i++) {

            // Concatenate the 3 values in the sequence to their strings. Don't add commas
            // on last iteration
            if (i != 2) {
                sequence += (sequenceStart + i) + ", ";
                correctAnswer += (correctAnswerStart + i) + ", ";
                wrongAnswer1 += (wrongAnswer1Start + i) + ", ";
                wrongAnswer2 += (wrongAnswer2Start + i) + ", ";
            } else {
                sequence += (sequenceStart + i);
                correctAnswer += (correctAnswerStart + i);
                wrongAnswer1 += (wrongAnswer1Start + i);
                wrongAnswer2 += (wrongAnswer2Start + i);
            }
        }

        // Add underscores to the end of the main sequence string
        sequence += ", __  __  __";

        // Randomly assign all 3 answers to different indicies in array. Run loops to
        // ensure all 3 are in unique places
        answers = new String[3];
        correctAnswerIndex = rand.nextInt(answers.length);
        answers[correctAnswerIndex] = correctAnswer;
        for (int i = 0; i < answers.length; i++) {
            if (answers[i] == null) {
                answers[i] = wrongAnswer1;
                break;
            }
        }
        for (int i = 0; i < answers.length; i++) {
            if (answers[i] == null) {
                answers[i] = wrongAnswer2;
                break;
            }
        }

    }

    public String getQuestionText() {
        return questionText;
    }

    public String getSequence() {
        return sequence;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String[] getAnswers() {
        return answers;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }
    /*
    Code for main driver to call this method 

    NextSequence nextSequence = new NextSequence();
        practicePanelTextLabel.setText(nextSequence.getQuestionText());
        String[] answers = nextSequence.getAnswers();
        int correctAnswerIndex = nextSequence.getCorrectAnswerIndex();
        practicePanelImageLabel.setText(nextSequence.getSequence());
     */



}

