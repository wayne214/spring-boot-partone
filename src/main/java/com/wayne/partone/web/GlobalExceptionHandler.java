package com.wayne.partone.web;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 设置一个 @ControllerAdvice 用来监控 Multipart 上传的文件大小是否受限，
 * 当出现此异常时在前端页面给出提示。
 * 利用 @ControllerAdvice 可以做很多东西，比如全局的统一异常处理等，
 * 感兴趣的读者可以抽空了解一下。
 *
 * */

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MultipartException.class)
    public String handleError1(MultipartException e, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message", e.getCause().getMessage());
        return "redirect:/uploadStatus";
    }
}
