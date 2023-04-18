// vue.config.js
module.exports = {
    publicPath: './',
    devServer: {
        proxy: {
            '/api': {
                target: 'http://121.41.89.199:2021/',
                changeOrigin: true,
                ws: true,
            }
        }
    }
}
