const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  publicPath:'./',
  transpileDependencies: true,
  lintOnSave:false,
  devServer:{
    proxy: {
      '/api': {
        target: 'http://36.133.127.248:8880',
        changeOrigin: true,
        secure: false,
        pathRewrite: {
          '^/api':''
        }
      }
    }
  },
})
