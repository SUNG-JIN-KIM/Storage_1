package com.k02.storage.controller;

import com.k02.storage.repository.MemoRepository;
import com.k02.storage.model.Memo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/memo")
public class MemoController {

    private final MemoRepository memoRepository;

    public MemoController(MemoRepository memoRepository) {
        this.memoRepository = memoRepository;
    }

    // 메모 목록 보기
    @GetMapping
    public String listMemos(Model model) {
        model.addAttribute("memos", memoRepository.findAll());
        return "memo-list";
    }

    // 메모 추가
    @PostMapping("/add")
    public String addMemo(@RequestParam String title,
                          @RequestParam String content) {
        LocalDateTime now = LocalDateTime.now();
        memoRepository.insert(title, content, now); // createdAt 저장
        return "redirect:/memo";
    }

    // 메모 삭제
    @PostMapping("/delete")
    public String deleteMemo(@RequestParam Long id) {
        memoRepository.delete(id);
        return "redirect:/memo";
    }

    // 수정 폼
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Memo memo = memoRepository.findById(id).orElseThrow();
        model.addAttribute("memo", memo);
        return "memo-edit";
    }

    // 메모 수정 처리
    @PostMapping("/edit")
    public String editMemo(@RequestParam("id") Long id,
                           @RequestParam("title") String title,
                           @RequestParam("content") String content) {
        LocalDateTime now = LocalDateTime.now();
        memoRepository.update(id, title, content, now); // updatedAt 저장
        return "redirect:/memo";
    }
}
