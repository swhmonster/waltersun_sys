module.exports = {
    publicPath: 'https://www.waltersun.cn/subsys',
    devServer: {
        // target host
        host: 'localhost',
        port: 2021,
        proxy: {
            '/context-path': {
                // 代理地址，这里设置的地址会代替axios中设置的baseURL
                target: 'http://192.168.159.27:2021/',
                // 如果接口跨域，需要进行这个参数配置
                changeOrigin: true,
                pathRewrite: {
                    '^/context-path': '/'
                }
            }
        }
    },
}
