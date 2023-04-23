// vue.config.js
module.exports = {
    publicPath: './',
    devServer: {
        proxy: {
            '/api': {
                // target: 'http://121.41.89.199:2021/',
                target: 'http://127.0.0.1:2021/',
                changeOrigin: true,
                ws: true,
            }
        }
    }
}
