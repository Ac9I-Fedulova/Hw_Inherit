import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TodosTest {
    SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

    String[] subtasks = {"Позвонить", "Яйца", "Хлеб"};
    Epic epic = new Epic(55, subtasks);

    Meeting meeting = new Meeting(
            555,
            "Выкатка 3й версии приложения",
            "Приложение НетоБанка",
            "Во вторник после обеда"
    );

    Todos todos = new Todos();

    @Test
    public void shouldAddThreeTasksOfDifferentType() {

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {simpleTask, epic, meeting};
        Task[] actual = todos.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchForMatchingTaskInSimpleTasks() { //полное соответствие текста
        todos.add(simpleTask);

        Boolean expected = true;
        Boolean actual = simpleTask.matches("Позвонить родителям");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldSearchForMatchingTaskByFirstWordInSimpleTasks() { // поиск только по первому слову
        todos.add(simpleTask);

        Boolean expected = true;
        Boolean actual = simpleTask.matches("Позвонить");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldSearchForMatchingTaskBySecondWordInSimpleTasks() { // поиск только по второму слову
        todos.add(simpleTask);

        Boolean expected = true;
        Boolean actual = simpleTask.matches("родителям");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldNotSearchForMatchingTaskInSimpleTasks() { // поиск недобавленой задачи в Симпл
        todos.add(simpleTask);

        Boolean expected = false;
        Boolean actual = simpleTask.matches("Покормить кошку");
        Assertions.assertEquals(expected, actual);
    }
    
    @Test
    public void shouldSearchForMatchingTaskInEpic() { //полное соответствие текста в одной из ячеек
        todos.add(epic);

        Boolean expected = true;
        Boolean actual = epic.matches("Хлеб");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldNotSearchForMatchingTaskInPhraseInEpic() { // словосочетание со словом из подзадачи
        todos.add(epic);

        Boolean expected = false;
        Boolean actual = epic.matches("Купить Яйца");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldNotSearchForMatchingTaskInEpic() { // поиск недобавленой в Эпик
        todos.add(epic);

        Boolean expected = false;
        Boolean actual = epic.matches("Огурец");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldSearchForMatchingTaskQueryFoundInTopic() { // поиск запроса по теме обсуждения
        todos.add(meeting);

        Boolean expected = true;
        Boolean actual = meeting.matches("Выкатка 3й версии приложения");
        Assertions.assertEquals(expected, actual);
    }

    // поиск запроса не по первому слову в названии проекта
    @Test
    public void shouldSearchForMatchingTaskNotByFirstWordQueryFoundInProject() {
        todos.add(meeting);

        Boolean expected = true;
        Boolean actual = meeting.matches("НетоБанка");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldNotSearchForMatchingTaskQueryFoundInStart() { //поиск запроса по дате и времи старта
        todos.add(meeting);

        Boolean expected = false;
        Boolean actual = meeting.matches("Во вторник после обеда");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldNotSearchForMatchingTaskInMeeting() { // по недобавленой задачи
        todos.add(meeting);

        Boolean expected = false;
        Boolean actual = meeting.matches("4ой");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldSearchTaskInFullCompliance() { // поиск существующей задачи полным текстом
        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {meeting};
        Task[] actual = todos.search("Выкатка 3й версии приложения");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchTasByOneWord() { // поиск существующих задач по одному слову
        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {simpleTask, epic};
        Task[] actual = todos.search("Позвонить");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchTaskNotByFirstWord() { // поиск существующей задачи не по первому слову
        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {simpleTask};
        Task[] actual = todos.search("родителям");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotSearchTask() { // поиск существующей задачи не по первому слову
        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {};
        Task[] actual = todos.search("Покормить кошку");
        Assertions.assertArrayEquals(expected, actual);
    }


}