import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class QuizApplication {
    private static class Question {
        String question;
        String[] options;
        int answer;

        Question(String question, String[] options, int answer) {
            this.question = question;
            this.options = options;
            this.answer = answer;
        }
    }

    private Question[] questions = {
        new Question("What is the capital of France?",
                     new String[]{"1. Paris", "2. Rome", "3. Berlin", "4. Madrid"}, 1),
        new Question("What is 5 + 3?",
                     new String[]{"1. 6", "2. 8", "3. 7", "4. 9"}, 2),
        new Question("Which programming language is known for AI?",
                     new String[]{"1. Python", "2. Java", "3. C++", "4. JavaScript"}, 1)
    };
    private int score = 0;

    public void startQuiz() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Quiz! You have 10 seconds to answer each question.\n");

        for (int i = 0; i < questions.length; i++) {
            Question q = questions[i];
            System.out.println("Question " + (i + 1) + ": " + q.question);
            for (String option : q.options) {
                System.out.println(option);
            }

            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    System.out.println("\nTime's up! Moving to the next question.\n");
                    System.exit(0); // End this question's timer
                }
            }, 10000);

            try {
                System.out.print("\nEnter the correct option number: ");
                int answer = scanner.nextInt();
                timer.cancel(); // Stop the timer

                if (answer == q.answer) {
                    System.out.println("Correct!\n");
                    score++;
                } else {
                    System.out.println("Wrong answer!\n");
                }
            } catch (Exception e) {
                System.out.println("Invalid input! Moving to the next question.\n");
                scanner.next(); // Clear invalid input
                timer.cancel();
            }
        }

        System.out.println("\nQuiz Over! Your final score is " + score + "/" + questions.length + ".");
        scanner.close();
    }

    public static void main(String[] args) {
        QuizApplication quiz = new QuizApplication();
        quiz.startQuiz();
    }
}