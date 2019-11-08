package com.shiyouao.swagger.controller;

import com.shiyouao.swagger.entity.Book;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.*;

/**
 * @Author sya
 * @Date 2019/11/7
 *
 * 用户创建某本图书	POST	/books/
 * 用户修改对某本图书	PUT	/books/:id/
 * 用户删除对某本图书	DELETE	/books/:id/
 * 用户获取所有的图书 GET /books
 *  用户获取某一图书  GET /Books/:id
 * 官方文档：http://swagger.io/docs/specification/api-host-and-base-path/
 * Swagger使用的注解及其说明：

 * @Api ：用在类上，说明该类的作用。

 * @ApiOperation ：注解来给API增加方法说明。

 * @ApiImplicitParams : 用在方法上包含一组参数说明。

 * @ApiImplicitParam ：用来注解来给方法入参增加说明。

 * @ApiResponses ：用于表示一组响应

 * @ApiResponse ：用在@ApiResponses中，一般用于表达一个错误的响应信息
 *   code：数字，例如400
 *   message：信息，例如"请求参数没填好"
 *   response：抛出异常的类

 * @ApiModel ：描述一个Model的信息（一般用在请求参数无法使用@ApiImplicitParam注解进行描述的时候）
 *   @ApiModelProperty ：描述一个model的属性


 * 注意：@ApiImplicitParam的参数说明：

 *   paramType：指定参数放在哪个地方
 *      header：请求参数放置于Request Header，使用@RequestHeader获取
 *      query：请求参数放置于请求地址，使用@RequestParam获取
 *      path：（用于restful接口）-->请求参数的获取：@PathVariable
 *      body：（不常用）
 *      form（不常用）
 *
 *  name：参数名

 *  dataType：参数类型

 *  required：参数是否必须传
 *   true | false

 *  value：说明参数的意思

 *  defaultValue：参数的默认值
 */
@RestController
@RequestMapping(value = "/books")
public class BookController {

    Map<Long, Book> books = Collections.synchronizedMap(new HashMap<Long, Book>());

    /**
     * Conntroller中定义的方法必须在@RequestMapper中显示的指定RequestMethod类型，
     * 否则SawggerUi会默认为全类型皆可访问， API列表中会生成多条项目。
     * @return
     */
    @ApiOperation(value="获取图书列表", notes="获取图书列表")
    @RequestMapping(value={"/getBook"}, method= RequestMethod.GET)
    public List<Book> getBook() {
        List<Book> book = new ArrayList<>(books.values());
        return book;
    }

    /**
     *
     * @param book
     * @return
     */
    @ApiOperation(value="创建图书", notes="创建图书")
    @ApiImplicitParam(name = "book", value = "图书详细实体", required = true, dataType = "Book")
    @RequestMapping(value="", method=RequestMethod.POST)
    public String postBook(@RequestBody Book book) {
        books.put(book.getId(), book);
        return "success";
    }

    /**
     * paramType会直接影响程序的运行期，如果paramType与方法参数获取使用的注解不一致，会直接影响到参数的接收。
     * @param id
     * @return
     */
    @ApiOperation(value="获图书细信息", notes="根据url的id来获取详细信息")
    @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "Long",paramType = "path")
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public Book getBook(@PathVariable Long id) {
        return books.get(id);
    }

    @ApiOperation(value="更新信息", notes="根据url的id来指定更新图书信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "图书ID", required = true, dataType = "Long",paramType = "path"),
            @ApiImplicitParam(name = "book", value = "图书实体book", required = true, dataType = "Book")
    })
    @RequestMapping(value="/{id}", method= RequestMethod.PUT)
    public String putUser(@PathVariable Long id, @RequestBody Book book) {
        Book book1 = books.get(id);
        book1.setName(book.getName());
        book1.setPrice(book.getPrice());
        books.put(id, book1);
        return "success";
    }
    @ApiOperation(value="删除图书", notes="根据url的id来指定删除图书")
    @ApiImplicitParam(name = "id", value = "图书ID", required = true, dataType = "Long",paramType = "path")
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public String deleteUser(@PathVariable Long id) {
        books.remove(id);
        return "success";
    }

    /**
     *  使用 @ApiIgnore注解忽略这个API
     * @return
     */
    @ApiIgnore
    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    public String  jsonTest() {
        return " hi you!";
    }
}

