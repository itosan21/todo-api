package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import com.example.demo.entity.Task;
import com.example.demo.repostiroy.TaskRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TaskServiceImplTest {

    private TaskServiceImpl taskService;

    private TaskRepository taskRepository;

    @BeforeEach
    public void setUp() {
        // モックオブジェクトの作成
        taskRepository = Mockito.mock(TaskRepository.class);

        // テスト対象クラスのインスタンス化（コンストラクタインジェクション）
        taskService = new TaskServiceImpl(taskRepository);
    }

    @AfterEach
    void tearDown() {
        // テストデータのクリーンアップなどの後処理を行う場合に使用します
    }

    @Test
    void testGetAllTasks() {
        // モックの設定
        Task task1 = new Task();
        Task task2 = new Task();
        List<Task> taskList = new ArrayList<>();
        taskList.add(task1);
        taskList.add(task2);

        // モックの振る舞いを定義
        Mockito.when(taskRepository.findAll()).thenReturn(taskList);

        // テスト実行
        List<Task> tasks = taskService.getAllTasks();

        // アサーション
        assertEquals(2, tasks.size());
        Mockito.verify(taskRepository, Mockito.times(1)).findAll();
    }

    @Test
    void testGetTaskById() {
        Task task = new Task();
        task.setId(1L);

        Mockito.when(taskRepository.findById(1L)).thenReturn(task);

        Task task1 = taskService.getTaskById(1L);

        assertEquals(1L,  task1.getId());
        Mockito.verify(taskRepository, Mockito.times(1)).findById(1L);

    }

    @Test
    void testCreateTask() {
        Task task = new Task();
        task.setTitle("ohtani");
        // task オブジェクトに必要なプロパティを設定してください

        Mockito.when(taskRepository.save(task)).thenReturn(task);

        Task createdTask = taskService.createTask(task);
        // テストするコードを追加してください
        assertEquals("ohtani", createdTask.getTitle());
        Mockito.verify(taskRepository, Mockito.times(1)).save(task);
    }

    @Test
    void testUpdateTask() {
        Task task1 = new Task();
        task1.setTitle("ohtani");

        Task task2 = new Task();
        task2.setTitle("trout");
        // task オブジェクトに必要なプロパティを設定してください

        Mockito.when(taskRepository.save(task2)).thenReturn(task2);
        task1 = taskService.updateTask(task2);
        // テストするコードを追加してください
        assertEquals("trout", task1.getTitle());
        Mockito.verify(taskRepository, Mockito.times(1)).save(task2);
    }

    @Test
    void testDeleteTask() {
        TaskServiceImpl task = new TaskServiceImpl(taskRepository);
        // モックの振る舞いを定義
        Mockito.doNothing().when(taskRepository).deleteById(1L);
        // テスト実行
        task.deleteTask(1L);

        // モックが期待通りに呼び出されたか検証
        Mockito.verify(taskRepository, Mockito.times(1)).deleteById(1L);
    }

    @Test
    void testToggleTaskStatus() {    // タスクを作成し、IDとステータスを設定
        Task task = new Task();
        task.setId(1L);
        task.setStatus("未完了");

        // モックの振る舞いを定義
        Mockito.when(taskRepository.findById(1L)).thenReturn((task));
        Mockito.when(taskRepository.save(any(Task.class))).thenAnswer(i -> i.getArguments()[0]);

        // テスト実行
        Task toggledTask = taskService.toggleTaskStatus(1L);

        // アサーション
        assertNotNull(toggledTask);
        assertEquals("完了", toggledTask.getStatus());
    }

}