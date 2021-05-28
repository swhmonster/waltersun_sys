module.exports = {
    publicPath: 'https://localhost:6767/subsys',
    // publicPath: 'https://www.waltersun.cn/subsys',
    devServer: {
        proxy: 'http://localhost:2021'
    }
}
