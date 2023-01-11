package personal.Tu.Controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import personal.Tu.Entity.Image;
import personal.Tu.Model.ImageModel;
import personal.Tu.Service.IImageService;

import javax.swing.text.html.parser.Entity;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("admin/Images") // Mac dinh la get
public class ImageController {
    @Autowired
    IImageService imageService;

    @Autowired
    Cloudinary cloudinary;

    @RequestMapping("")
    public String listImages(ModelMap modelMap){
        List<Image> list = imageService.findAll();
        modelMap.addAttribute("Images", list);
        return "admin/images/list";
    }

    @GetMapping("add")
    public String addImages(ModelMap modelMap){
        ImageModel imageModel = new ImageModel();
        modelMap.addAttribute("Image", imageModel);
        return "admin/images/add";
    }

    @PostMapping("saveOrUpdate")
    public ModelAndView saveOrUpdate(ModelMap model, @Valid @ModelAttribute("Image") ImageModel image, BindingResult result){
        if (result.hasErrors()){
            System.out.println(result.getAllErrors());
            String message = "Thất bại !!!";
            model.addAttribute("message", message);
            return new ModelAndView("forward:/admin/Images/add", model);
        }
        Image ImageEntity = new Image();
        // Coppy từ model sang entity
        BeanUtils.copyProperties(image, ImageEntity);

        System.out.println("Check: " + image.getImageFile().isEmpty());
        // Xử lý hình ảnh lưu file vào cloudinary
        if (!image.getImageFile().isEmpty()){
            System.out.println(
                    "vao if"
            );
            try {
                Map r = this.cloudinary.uploader().upload(image.getImageFile().getBytes(),
                        ObjectUtils.asMap("resource_type","auto"));
                String img = (String) r.get("secure_url");
                System.out.println("vao Try");
                System.out.println(img);
                ImageEntity.setImage(img);
            } catch (IOException e){
                e.printStackTrace();
            }

        }
        System.out.println("truoc khi save");
        imageService.save(ImageEntity);
        String message = "Image đã được cập nhật thành công";
        model.addAttribute("message", message);
        return new ModelAndView("forward:/admin/Images",model);
    }
    @GetMapping("delete/{ID}")
    public ModelAndView delete(ModelMap model, @PathVariable("ID") int ID){
        imageService.deleteById(ID);
        model.addAttribute("message","Xóa thành công");
        return new ModelAndView("forward:/admin/Images", model);
        // Redirect : Chuyển nội dung qua 1 trang mới
        // forward : Nhúng nội dung vào 1 trang nào đó
    }
    @GetMapping("edit/{ID}")
    public ModelAndView edit(ModelMap model, @PathVariable("ID") int ID){
        Optional<Image> optional = imageService.findById(ID);
        ImageModel imageModel = new ImageModel();
        if(optional.isPresent()){
            Image image = optional.get();
            BeanUtils.copyProperties(image, imageModel);
            model.addAttribute("Image", imageModel);
            return new ModelAndView("/admin/images/add");
        }
        model.addAttribute("message","Image không tồn tại!!");
        return new ModelAndView("forward:admin/Images");
    }

    @GetMapping("search")
    public String search(ModelMap model, @RequestParam(name="name", required = false) String name ){
        List<Image> list = null;
        if(StringUtils.hasText(name)){
            list = imageService.findByNameContaining(name);
            System.out.println("vao If");
        }else {
            list = imageService.findAll();
            System.out.println("vao else");
        }
        model.addAttribute("name", name);
        model.addAttribute("Images", list);
        System.out.println(list);
        return "admin/images/list";
    }
}
