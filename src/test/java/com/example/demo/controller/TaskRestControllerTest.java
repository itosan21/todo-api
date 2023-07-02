package com.example.demo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import com.example.demo.entity.Task;
import com.example.demo.service.TaskService;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
public class TaskRestControllerTest {

    @Mock
    private TaskService taskService;

    @Autowired
    private TaskRestController target;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        // モックオブジェクトの作成
        MockitoAnnotations.openMocks(this);

        target = new TaskRestController(taskService);

        mockMvc = MockMvcBuilders.standaloneSetup(target).build();

    }

    @AfterEach
    void tearDown() {
        // テストデータのクリーンアップなどの後処理を行う場合に使用します
    }

    @Test
    void testGetAllTasks() throws Exception {
        // モックの設定
        Task task1 = new Task(1L, "Task1", "Description1", "未完了");
        Task task2 = new Task(2L, "Task2", "Description2", "未完了");

        List<Task> tasks = Arrays.asList(task1, task2);

        Mockito.doReturn(tasks).when(taskService).getAllTasks();

        mockMvc.perform(get("/task")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        Mockito.verify(taskService, Mockito.times(1)).getAllTasks();
    }

}