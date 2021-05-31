// vue.config.js
module.exports = {
    /*serve 的问题在于它把 dist/ 和 dist 等价了。这是错误的处理。
    URL 路径中的 ./ 是完全没有意义可以消掉的，但是结尾的 / 表示这个路径是一个目录，不带 / 表示是一个文件。
    习惯是静态服务器用 dist/index.html 响应目录请求 dist/，但是 serve 直接把这个请求重定向到了 dist，这肯定是错的，目录层级都不对了。
    要是有兴趣可以去给 serve 提 bug，但这不是 Vue CLI 的问题。*/
    publicPath: './',
    devServer: {
        proxy: {
            target: process.env.NODE_ENV === 'development'
                ?'http://localhost:2021':'http://www.waltersun.cn',
            changeOrigin: true,
            pathRewrite: {
                '^/api': '/api'
            }
        }
    },
    pages: {
        index: {
            // page 的入口
            // entry: 'src/index/main.js',
            // 模板来源
            // template: 'public/index.html',
            // 在 dist/index.html 的输出
            // filename: 'index.html',
            // 当使用 title 选项时，
            // template 中的 title 标签需要是 <title><%= htmlWebpackPlugin.options.title %></title>
            title: 'Walter\'s WebSite',
            // 在这个页面中包含的块，默认情况下会包含
            // 提取出来的通用 chunk 和 vendor chunk。
            // chunks: ['chunk-vendors', 'chunk-common', 'index']
        },
        // 当使用只有入口的字符串格式时，
        // 模板会被推导为 `public/subpage.html`
        // 并且如果找不到的话，就回退到 `public/index.html`。
        // 输出文件名会被推导为 `subpage.html`。
        // subpage: 'src/subpage/main.js'
    }
}
