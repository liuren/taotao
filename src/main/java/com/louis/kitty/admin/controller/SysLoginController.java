package com.louis.kitty.admin.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.louis.kitty.admin.model.SysUser;
import com.louis.kitty.admin.model.SysUserToken;
import com.louis.kitty.admin.result.HttpResult;
import com.louis.kitty.admin.service.SysUserService;
import com.louis.kitty.admin.service.SysUserTokenService;
import com.louis.kitty.admin.utils.PasswordUtils;
import com.louis.kitty.admin.utils.ShiroUtils;
import com.louis.kitty.admin.vo.LoginBean;

@RestController
public class SysLoginController {

    @Autowired
    private SysUserService sysUserService;
    
    @Autowired
    private SysUserTokenService sysUserTokenService;
    
    @Autowired
    private Producer producer;
    
    @GetMapping("/captcha.jpg")
    public void captcha(HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
      // 生成文字验证码
      String text = producer.createText();
      // 生成图片验证码
      BufferedImage image = producer.createImage(text);
      // 保存到shiro session
      ShiroUtils.setSessionAttribute(Constants.KAPTCHA_SESSION_KEY, text);

      ServletOutputStream out = response.getOutputStream();
      ImageIO.write(image, "jpg", out);       
      IOUtils.closeQuietly(out);
    }
    
    /**
     * 登录接口
     */
    @PostMapping(value = "/login")
    public HttpResult login(@RequestBody LoginBean loginBean) throws IOException{
        String username = loginBean.getAccount();
        String password = loginBean.getPassword();
        String captcha = loginBean.getCaptcha();
        
        // 从session中获取之前保存的验证码跟前台传来的验证码进行匹配
        Object kaptcha = ShiroUtils.getSessionAttribute(Constants.KAPTCHA_SESSION_KEY);
        if(kaptcha == null){
            return HttpResult.error("验证码已失效");
        }
        if(!captcha.equals(kaptcha)){
            return HttpResult.error("验证码不正确");
        }
        
        //用户信息
        SysUser user = this.sysUserService.findByName(username);
        
        //账号不存在
        if (user == null) {
            return HttpResult.error("账号不存在");
        }
        
        if (!match(user,password)) {
            return HttpResult.error("密码不正确");
        }
        
        if (user.getStatus() == 0) {
            return HttpResult.error("账号已被锁定,请联系管理员");
        }
        
        //生成token，并保存到数据库
        SysUserToken data = this.sysUserTokenService.createToken(user.getId());
        return HttpResult.ok(data);
    }

    /**
     * 验证用户密码
     * @param user
     * @param password
     * @return
     */
    public boolean match(SysUser user, String password) {
        return user.getPassword().equals(PasswordUtils.encrypte(password, user.getSalt()));
    }
    
    /**
     * 登出接口
     */
    @GetMapping(value = "/logout")
    public HttpResult logout() {
        ShiroUtils.logout();
        return HttpResult.ok();
    }
}
