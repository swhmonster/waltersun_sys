// vue.config.js
module.exports = {
    publicPath: './',
    devServer: {
        proxy: {
            '/api': {
                target: 'http://192.168.159.27:2021/',
                changeOrigin: true,
                ws: true,
            }
        }
    }
}
